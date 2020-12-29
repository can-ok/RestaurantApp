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
import org.springframework.data.domain.*;

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

    private Pageable pages= PageRequest.of(0,5);

    @Before
    public void setUpProductsList(){


        Set<ProductCategory> setCategory=new HashSet<>( );
        setCategory.add(new ProductCategoryBuilder().id(1).name("deneme").build());

        product=new ProductBuilder().id(1).title("deneme").description("descript").price(10).productcategory(setCategory).build();

        productDto=ProductDtoConverter.convertDrinktoDrinkDto(product);

        productDtoList.add(productDto);

    }




    @Test
    public void shouldgetProduct() {
        Page<ProductDto> products=new PageImpl<>(productDtoList);
        when(productsService.getProducts(0,5)).thenReturn(products);

        assertEquals(productsController.getAllDrinks(0,5).getTotalElements(),1);

    }

    @Test
    public void shouldNotgetProduct() {
        when(productsService.getAllDrinks(0,5)).thenReturn(null);

        assertNull(productsController.getAllDrinks(0,5));
    }


    @Test
    public void shouldgetSliceProduct() {
        Slice<ProductDto> products=new SliceImpl<>(productDtoList);
        when(productsService.getAllDrinks(0,5)).thenReturn(products);

        assertEquals(productsController.getAllProducts(0,5).hasNext(),false);

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
        assertEquals(productsController.retriveProduct(id),productDto);

    }

    @Test
    public void deleteProduct() {
        when(productsService.deleteDrink(any())).thenReturn("Success");
        assertEquals(productsController.deleteDrink(1),"Success");
    }



    @Test
    public void updateProduct() {
        when(productsService.updateDrink(any())).thenReturn(productDto);
        assertEquals(productsController.updateDrink(any()),productDto);

    }


    @Test
    public void retrivebyProductCategor() {
        int id=1;
        Slice<ProductDto> products=new SliceImpl<>(productDtoList);
        when(productsService.getSpecificCategory(id,0,5)).thenReturn(products);

        assertEquals(productsController.retrivebyProductCategor(id,0,5).hasNext(),false);

        //verify(productsController)
    }
}