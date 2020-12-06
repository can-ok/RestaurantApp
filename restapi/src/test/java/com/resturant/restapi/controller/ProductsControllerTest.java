package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.DrinkDto;
import com.resturant.restapi.dto.FoodDto;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.repository.FoodRepository;
import com.resturant.restapi.service.ProductsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import java.util.*;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;



@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {

    @Mock
    private ProductsService productsService;

    @Mock
    private FoodRepository foodRepository;


    @InjectMocks
    private ProductsController productsController;


    List<DrinkDto> drinkDtoList=new ArrayList<>();

    @Before
    public void setUpDrinksList(){
        ProductCategoryDto productCategory=new ProductCategoryDto(1,"deneme");
        drinkDtoList.add(new DrinkDto(1,"deneme","deneme descrtip",20,productCategory));
    }


    DrinkDto drinkDto=new DrinkDto();
    @Before
    public void setUpDrink(){
        drinkDto.setId(1);
        ProductCategoryDto productCategory=new ProductCategoryDto(1,"deneme");
        drinkDto.setProductcategory(productCategory);
    }

    FoodDto foodDto=new FoodDto();
    @Before
    public void setUpFood(){
        foodDto.setId(1);
        ProductCategoryDto productCategory=new ProductCategoryDto(1,"deneme");
        foodDto.setProductcategory(productCategory);
    }



    @Before
    public void setUpRepos(){
        when(productsService.getAllDrinks()).thenReturn(drinkDtoList);
        when(productsService.getAllFoods()).thenReturn(foodsDtoList);
    }

    List<FoodDto> foodsDtoList=new ArrayList<>();

    @Before
    public void setUpFoodsList(){
        ProductCategoryDto productCategory=new ProductCategoryDto(1,"deneme");
        foodsDtoList.add(new FoodDto(1,"deneme","deneme descrtip",20,productCategory));
    }

    @Test
    public void shouldgetAllDrinks() {
        when(productsService.getAllDrinks()).thenReturn(drinkDtoList);

        assertNotNull(productsController.getAllDrinks());
        verify(productsService,times(1)).getAllDrinks();
    }

    @Test
    public void shouldNotgetAllDrinks() {
        when(productsService.getAllDrinks()).thenReturn(null);

        assertNull(productsController.getAllDrinks());
    }

    @Test
    public void shouldgetAllFoods() {

        when(productsService.getAllFoods()).thenReturn(foodsDtoList);

        assertNotNull(productsController.getAllFoods());
        verify(productsService,times(1)).getAllFoods();
    }

    @Test
    public void shoulGetAllProducts(){

        List<? extends ProductDto> prodResult=productsController.getAllProducts();

        assertNotNull(prodResult);

        assertEquals(prodResult.size(),drinkDtoList.size()+foodsDtoList.size());
    }

    @Test
    public void addFood() {

        when(productsService.insertFood(foodDto,1)).thenReturn("Success");


        assertEquals(productsController.addFood(foodDto,1),"Success");

        //verify(productsService,times(1)).insertFood(any(),any());
    }

    @Test
    public void addDrink() {

        when(productsService.insertDrink(drinkDto,1)).thenReturn("Success");
        assertEquals(productsController.addDrink(drinkDto,1),"Success");

    }

    @Test
    public void retriveDrink() {
        int id=1;
        when(productsService.getDrinkById(id)).thenReturn(drinkDto);
        assertEquals(productsController.retriveDrink(id),drinkDto);

    }

    @Test
    public void retriveFood() {
        int id=1;

        when(productsService.getFoodById(id)).thenReturn(foodDto);
        assertEquals(productsController.retriveFood(id),foodDto);
    }

    @Test
    public void deleteFood() {

        when(productsService.deleteFood(any())).thenReturn(foodsDtoList);
        assertNotNull(productsController.deleteFood(any()));
        verify(productsService,times(1)).deleteFood(any());
    }

    @Test
    public void deleteDrink() {

        when(productsService.deleteDrink(any())).thenReturn(drinkDtoList);
        assertNotNull(productsController.deleteDrink(any()));
        verify(productsService,times(1)).deleteDrink(any());

    }

    @Test
    public void updateFood() {
        when(productsService.updateFood(any(),any())).thenReturn(foodDto);
        assertEquals(productsController.updateFood(any(),any()),foodDto);
    }

    @Test
    public void updateDrink() {
        when(productsService.updateDrink(any(),any())).thenReturn(drinkDto);

        assertEquals(productsController.updateDrink(any(),any()),drinkDto);

    }


    @Test
    public void retrivebyProductCategor() {
        int id=1;
        when(productsService.getSpecificCategory(id)).thenReturn(null);

        assertNull(productsController.retrivebyProductCategor(id));

        //verify(productsController)
    }
}