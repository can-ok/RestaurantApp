package com.resturant.restapi.service;

import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.ProductDtoConverter;
import com.resturant.restapi.converter.ProductsCategoryDtoConverter;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.repository.ProductRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductsService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productcategoryRepository;



    public List<ProductDto> getAllDrinks(){

        List<ProductDto> productDtoList =new ArrayList<>();

        productDtoList = ProductDtoConverter.convertDrinkListToDrinDtoList(productDtoList, productRepository.findAll());

        return productDtoList;

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

        Product product =new Product();

        for(ProductCategoryDto productcategoryDto: productDto.getProductcategory())
        {
            Optional<ProductCategory> productcategory=productcategoryRepository.findById(productcategoryDto.getId());

            if(productcategory.isPresent()){

                product =ProductDtoConverter.convertDrinkDtoToDrink(product, productDto,productcategory);


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

        Optional<Product> drinkEntity= productRepository.findById(id);
        if(drinkEntity.isPresent()){

            //drinkEntity.get().setProductcategory(null);

            productRepository.delete(drinkEntity.get());
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

            List<ProductCategory> prodList=ProductsCategoryDtoConverter.prodCategoryDtoSetToProdCategoryList(productDto.getProductcategory());
            optinalDrink.get().setProductcategory(ProductsCategoryDtoConverter.prodCategoryDtoListToProductCategoryList(productDto.getProductcategory()));

            productRepository.save(optinalDrink.get());

            return productDto;
        }
    }



}
