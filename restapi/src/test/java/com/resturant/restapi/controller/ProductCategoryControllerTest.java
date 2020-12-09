/*
package com.resturant.restapi.controller;

import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.service.ProductCategoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryControllerTest {

    @Mock
    ProductCategoryService productCategoryService;

    @InjectMocks
    ProductCategoryController productCategoryController;

    List<ProductCategoryDto> productCategoryDtoList=new ArrayList<>();
    ProductCategoryDto productCategoryDto=new ProductCategoryDto();

    @Before
    public void SetCategory(){

        productCategoryDto.setName("Sıcak");
        productCategoryDto.setId(1);
    }


    @Before
    public void SetCategoryList(){
        productCategoryDtoList.add(productCategoryDto);
    }

    @Test
    public void getAllCategories() {
        when(productCategoryService.getAll()).thenReturn(productCategoryDtoList);

        assertEquals(productCategoryDtoList,productCategoryController.getAllCategories());
    }

    @Test
    public void saveCategory() {
        when(productCategoryService.insertCatagory(any())).thenReturn(productCategoryDto);
        assertEquals(productCategoryController.saveCategory(productCategoryDto),productCategoryDto);
    }

    @Test
    public void deleteCategory() {
        String msg="Success";
        int id=1;
        when(productCategoryService.deleteUser(id)).thenReturn(msg);

        assertEquals(msg,productCategoryController.deleteCategory(id));
    }

    @Test
    public void updateCategory() {
        int id=1;
        when(productCategoryService.updateCategory(id,productCategoryDto)).thenReturn(productCategoryDto);

        assertSame(productCategoryDto,productCategoryController.updateCategory(id,productCategoryDto));

    }


    @After
    public void deletList(){

        productCategoryDtoList.remove(productCategoryDto);
    }
}*/
