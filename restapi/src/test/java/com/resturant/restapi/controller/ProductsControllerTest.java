package com.resturant.restapi.controller;


import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.builder.ProductBuilder;
import com.resturant.restapi.builder.ProductCategoryBuilder;
import com.resturant.restapi.converter.ProductDtoConverter;
import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.service.ProductsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import java.util.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;



@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {

    @Mock
    private ProductsService productsService;




    @InjectMocks
    private ProductsController productsController;


    List<ProductDto> productDtos =new ArrayList<>();

    private Product product;

    private ProductDto productDto;
    List<ProductDto> productDtoList=new ArrayList<>();
    @Before
    public void setUpProductsList(){


        Set<ProductCategory> setCategory=new HashSet<>( );
        setCategory.add(new ProductCategoryBuilder().id(1).name("deneme").build());

        product=new ProductBuilder().id(1).title("deneme").description("descript").price(10).productcategory(setCategory).build();

        productDto=ProductDtoConverter.convertDrinktoDrinkDto(product);

    }




    @Test
    public void shouldgetProduct() {
        when(productsService.getAllDrinks()).thenReturn(productDtos);

        assertNotNull(productsController.getAllDrinks());
        verify(productsService,times(1)).getAllDrinks();

    }

    @Test
    public void shouldNotgetProduct() {
        when(productsService.getAllDrinks()).thenReturn(null);

        assertNull(productsController.getAllDrinks());
    }






    @Test
    public void addProduct() {

        when(productsService.insertDrink(productDto)).thenReturn("Success");
        assertEquals(productsController.addDrink(productDto),"Success");

    }

    @Test
    public void retriveProduct() {
        int id=1;
        when(productsService.getDrinkById(id)).thenReturn(productDto);
        assertEquals(productsController.retriveDrink(id),productDto);

    }




    @Test
    public void deleteProduct() {

        when(productsService.deleteDrink(any())).thenReturn(productDtos);
        assertNotNull(productsController.deleteDrink(any()));
        verify(productsService,times(1)).deleteDrink(any());

    }



    @Test
    public void updateProduct() {
        when(productsService.updateDrink(any())).thenReturn(productDto);

        assertEquals(productsController.updateDrink(any()),productDto);

    }


    @Test
    public void retrivebyProductCategor() {
        int id=1;
        when(productsService.getSpecificCategory(id)).thenReturn(null);

        assertNull(productsController.retrivebyProductCategor(id));

        //verify(productsController)
    }
}