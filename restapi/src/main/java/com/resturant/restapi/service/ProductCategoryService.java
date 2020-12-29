package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.ProductMapper;
import com.resturant.restapi.converter.ProductsCategoryDtoConverter;
import com.resturant.restapi.converter.ProductsCategoryMapper;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productcategoryRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    ProductsCategoryMapper productsCategoryMapper;

    @Autowired
    private MessageSourceExternalizer messageSourceExternalizer;

    @Cacheable(cacheNames = "catagories")
    public Set<ProductCategoryDto> getAll(){
        Set<ProductCategoryDto> productCategoryDtoList=new HashSet<>();
        productcategoryRepository.findAll().forEach(productCategory -> {
            ProductCategoryDto productCategoryDto=productsCategoryMapper.toDto(productCategory);
            productCategoryDtoList.add(productCategoryDto);
        });
       // productsCategoryMapper.toProductCategorySet()
        return productCategoryDtoList;
    }

    @CacheEvict(value="catagories" ,allEntries = true)
    public ProductCategoryDto insertCatagory(ProductCategoryDto categoryDto){
        if (categoryDto==null || categoryDto.getId()<1  || categoryDto.getCategorymedia().getId()<1){
            throw new ContentNotAllowed("Category content not allowed");
        }

        Optional<Media> media=mediaRepository.findById(categoryDto.getCategorymedia().getId());
        if(!media.isPresent()){
            throw new EntityNotFound("Media "+messageSourceExternalizer.getMessage("entity.error"));
        }
        ProductCategory productCategory = productsCategoryMapper.toEntity(categoryDto);
        productCategory.setCategorymedia(media.get());


        productcategoryRepository.save(productCategory);
        return categoryDto;
    }

    @CacheEvict(value="catagories",allEntries = true)
    public String deleteUser(Integer id){
        Optional<ProductCategory> byId = productcategoryRepository.findById(id);
        if (!byId.isPresent())
        {
            throw new EntityNotFound("Category "+messageSourceExternalizer.getMessage("entity.error"));
        }
        byId.get().setCategorymedia(null);
        productcategoryRepository.deleteById(id);
        return "Success";
    }


    @CachePut(value = "CategoryCache",key = "'Category_CACHE_BY_ID_'.concat(#id)")
    @CacheEvict(value="catagories" ,allEntries = true)
    public ProductCategoryDto updateCategory(Integer id,ProductCategoryDto category){

        if(category==null || id<1){
            throw new ContentNotAllowed("Content not allowed");
        }

        Optional<ProductCategory> entity=productcategoryRepository.findById(id);

        if(!entity.isPresent()){

           throw new EntityNotFound("Category "+messageSourceExternalizer.getMessage("entity.error"));
        }
        else{

            ProductCategory productCategory = entity.get();
            if(!productCategory.getName().equals(category.getName())){
                productCategory.setName(category.getName());
            }
            if(category.getCategorymedia()!= productCategory.getCategorymedia()){
                Media updateMedia=mediaRepository.findById(productCategory.getCategorymedia().getId()).get();
                productCategory.setCategorymedia(updateMedia);
            }
            if(productCategory.getId()!=category.getId()){
                productCategory.setId(id);
            }
            if(!productCategory.getDescription().equals(category.getDescription())){
                productCategory.setDescription(category.getDescription());

            }
            productcategoryRepository.save(productCategory);
            return category;
        }
    }

    @Cacheable(value = "CategoryCache",key = "'Category_CACHE_BY_ID_'.concat(#id)")
    public ProductCategoryDto getDrinkById(int id){
        //id null check
        if(!productcategoryRepository.findById(id).isPresent())
        {
            throw new EntityNotFound("Category "+messageSourceExternalizer.getMessage("entity.error"));
        }

        return productsCategoryMapper.toDto(productcategoryRepository.findById(id).get());
    }

}
