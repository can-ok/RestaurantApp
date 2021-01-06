package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.ProductsCategoryMapper;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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


        productCategoryDtoList=productsCategoryMapper.toProductCategoryListDtoSet(productcategoryRepository.findAll());
        return productCategoryDtoList;
    }

    @CacheEvict(value="catagories" ,allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductCategoryDto insertCatagory(ProductCategoryDto categoryDto){

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
    @Transactional(propagation = Propagation.REQUIRED)
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
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductCategoryDto updateCategory(Integer id,ProductCategoryDto category){


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
        if(!productcategoryRepository.findById(id).isPresent())
        {
            throw new EntityNotFound("Category "+messageSourceExternalizer.getMessage("entity.error"));
        }

        return productsCategoryMapper.toDto(productcategoryRepository.findById(id).get());
    }

}
