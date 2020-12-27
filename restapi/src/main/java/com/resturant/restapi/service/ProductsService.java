package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.ProductDtoConverter;
import com.resturant.restapi.converter.ProductMapper;
import com.resturant.restapi.converter.ProductsCategoryDtoConverter;
import com.resturant.restapi.converter.ProductsCategoryMapper;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductsService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productcategoryRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductsCategoryMapper productsCategoryMapper;

    public Slice<ProductDto> getAllDrinks(int pageCount,int pageSize){

        Pageable pages=PageRequest.of(pageCount,pageSize);

        Slice<ProductDto> productDtosSlice=productRepository.findAllProd(pages).map(productMapper::toDto);


        return productDtosSlice;

    }


    public Page<ProductDto> getProducts(int pageCount,int pageSize){

        if(pageCount<0 && pageSize<0)
        {
            throw new ContentNotAllowed("Not allowed page interval");
        }
        Pageable pageable=PageRequest.of(pageCount,pageSize);

        Page<Product> productList=productRepository.findAllProducts(pageable);

        Page<ProductDto> productDtos=productRepository.findAllProducts(pageable).map(productMapper::toDto);

        for(int i=0; i<productList.getContent().size(); i++){

            productDtos.getContent().get(i).setProductcategory(productsCategoryMapper.toProductCategoryDtoSet(productList.getContent().get(i).getProductcategory()));
            //productDtos.get(i).setProductcategory(productsCategoryMapper .toProductCategoryDtoSet(productList.get(i).getProductcategory()));
        }

        return productDtos;
    }



    public Slice<ProductDto> getSpecificCategory(int ProductCategoryId,int pageCount,int size){
        if(pageCount<0 && size<0)
        {
            throw new ContentNotAllowed("Not allowed page interval");
        }
        Pageable pages=PageRequest.of(pageCount,size);
        Slice<ProductDto> productByProductcategoryId = productRepository.findProductByProductcategoryId(ProductCategoryId, pages).map(productMapper::toDto);

        return productByProductcategoryId;
    }




    public String insertDrink(ProductDto productDto){
        if(productDto==null){
            throw new ContentNotAllowed("Product Content Not Allowed");
        }
        if(productDto.getTitle()==null ||productDto.getTitle().equals("") ){
            throw new ContentNotAllowed("Product Content Not Allowed");
        }
        if(productDto.getProductcategory().isEmpty()){
            throw new ContentNotAllowed("Product Content Not Allowed");
        }

        Product product=productMapper.toEntity(productDto);

        for(ProductCategoryDto productcategoryDto: productDto.getProductcategory())
        {
            Optional<ProductCategory> productcategory=productcategoryRepository.findById(productcategoryDto.getId());

            Optional<Media> media=mediaRepository.findById(productDto.getMedia().getId());
            if(!productcategory.isPresent() && !media.isPresent()){
                //product =ProductDtoConverter.convertDrinkDtoToDrink(product, productDto,productcategory,media.get());
                throw new EntityNotFound("Product/Media entity not found");
            }
            product.getProductcategory().add(productcategory.get());
            product.setMedia(media.get());

        }
        productRepository.save(product);
        return "Success";
    }




    public ProductDto getDrinkById(Integer id){
        Optional<Product> optinalEntity= productRepository.findById(id);

        if(!optinalEntity.isPresent()){

            throw  new EntityNotFound("Product Not Found");
        }

        ProductDto productDto = productMapper.toDto(optinalEntity.get());

        return productDto;

    }



    public String deleteDrink(Integer id){

        Optional<Product> productEntity= productRepository.findById(id);
        if(!productEntity.isPresent()){
            throw  new EntityNotFound("Product Not Found");
        }

        productEntity.get().setProductcategory(null);
        productEntity.get().setMedia(null);
        productRepository.delete(productEntity.get());
        return "Succes";


    }



    public ProductDto updateDrink(ProductDto productDto){

        if(productDto==null|| productDto.getId()<1){
            throw new ContentNotAllowed("Product Content Not Allowed");
        }

        Optional<Product> optinalDrink = productRepository.findById(productDto.getId());

        if (!optinalDrink.isPresent()) {
           throw new EntityNotFound("Product Not Found");
        }
        else{

            if(optinalDrink.get().getDescription()!=productDto.getDescription()){
                optinalDrink.get().setDescription(productDto.getDescription());
            }
            if(optinalDrink.get().getPrice()!=productDto.getPrice())
            {
                optinalDrink.get().setPrice(productDto.getPrice());
            }
            if(optinalDrink.get().getTitle()!=productDto.getTitle()){
                optinalDrink.get().setTitle(productDto.getTitle());
            }


            productDto.getProductcategory().forEach(productCategoryDto -> {
                Optional<ProductCategory> productcategory=productcategoryRepository.findById(productCategoryDto.getId());
                optinalDrink.get().getProductcategory().add(productcategory.get());
            });


            Optional<Media> media=mediaRepository.findById(productDto.getMedia().getId());
            optinalDrink.get().setMedia(media.get());
            productRepository.save(optinalDrink.get());

            return productDto;
        }
    }



}
