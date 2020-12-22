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
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public List<ProductDto> getAllDrinks(){

        List<Product> productList=productRepository.findAll();
        List<ProductDto> listProductDto=productMapper.toDtoList(productRepository.findAll());

        for(int i=0; i<productList.size(); i++){
            listProductDto.get(i).setProductcategory(productsCategoryMapper .toProductCategoryDtoSet(productList.get(i).getProductcategory()));
        }

        return listProductDto;

    }



    public List<ProductDto> getSpecificCategory(int ProductCategoryId){


        List<Product> listdrink= productRepository.findProductByProductcategoryId(ProductCategoryId);


        List<ProductDto> productDtoDtoList =new ArrayList<>();


        if(!listdrink.isEmpty())
        {
            return ProductDtoConverter.convertDrinkListToDrinDtoList(productDtoDtoList,listdrink);
        }
        else {

            return Collections.emptyList();
        }
        
    }





    public String insertDrink(ProductDto productDto){
        Product product=productMapper.toEntity(productDto);

        for(ProductCategoryDto productcategoryDto: productDto.getProductcategory())
        {
            Optional<ProductCategory> productcategory=productcategoryRepository.findById(productcategoryDto.getId());
            Optional<Media> media=mediaRepository.findById(productDto.getMedia().getId());
            if(productcategory.isPresent()){
                //product =ProductDtoConverter.convertDrinkDtoToDrink(product, productDto,productcategory,media.get());
                product.getProductcategory().add(productcategory.get());
                product.setMedia(media.get());
            }
            else{
                return "Fail";
            }

        }
        productRepository.save(product);
        return "Success";
    }




    public ProductDto getDrinkById(Integer id){
        Optional<Product> optinalEntity= productRepository.findById(id);

        if(!optinalEntity.isPresent()){

            return null;
        }

        else{

            ProductDto productDto = ProductDtoConverter.convertDrinktoDrinkDto(optinalEntity.get());
            return productDto;
        }
    }




    public List<ProductDto> deleteDrink(Integer id){

        List<ProductDto> productDtos =new ArrayList<>();

        Optional<Product> productEntity= productRepository.findById(id);
        if(productEntity.isPresent()){

            productEntity.get().setProductcategory(null);
            productEntity.get().setMedia(null);
            productRepository.delete(productEntity.get());
            productDtos =getAllDrinks();

        }

        return productDtos;



    }



    public ProductDto updateDrink(ProductDto productDto){

        Optional<Product> optinalDrink = productRepository.findById(productDto.getId());

        if (!optinalDrink.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;
        }
        else{

            optinalDrink.get().setId(productDto.getId());
            optinalDrink.get().setDescription(productDto.getDescription());
            optinalDrink.get().setPrice(productDto.getPrice());
            optinalDrink.get().setTitle(productDto.getTitle());


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
