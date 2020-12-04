package com.resturant.restapi.service;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.DtoConverter;
import com.resturant.restapi.dto.DrinkDto;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.repository.DrinksRepository;
import com.resturant.restapi.repository.FoodRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductsServiceSetAndUpdateTest {

    @Mock
    private DrinksRepository drinksRepository;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductsService productsService;

    private Drink drink=new Drink();


    private ProductCategory productCategory=new ProductCategory();


    @Before
    public void setUpDrinkEntity() throws Exception{

        drink.setTitle("deneme");
        drink.setId(1);
        drink.setTitle("title");
        drink.setPrice(10);
        drink.setDescription("Deneme Item");

        productCategory.setName("deneme");
        productCategory.setId(1);
        drink.setProductcategory(productCategory);
        // add list
    }




    @Test
    public void shouldNotInsertFood() {
        // ın that scenario we assume that catagory doesnt exit so it goes fail
        Mockito.when(drinksRepository.save(any())).thenReturn(drink);
        String response=productsService.insertDrink(DtoConverter.convertDrinktoDrinkDto(drink),1);

        assertNotNull(response);
        assertEquals(response,"fail");
    }

    @Test
    public void insertDrink() {

        when(productCategoryRepository.findById(any())).thenReturn(Optional.of((productCategory)));

        Mockito.when(drinksRepository.save(any())).thenReturn(drink);

        String response=productsService.insertDrink(DtoConverter.convertDrinktoDrinkDto(drink),1);

        assertNotNull(response);
        assertEquals(response,"Success");
    }

    @Test
    public void updateFood() {


    }

    @Test
    public void shouldupdateDrink() {
        int id=1;
        Mockito.when(drinksRepository.findById(id)).thenReturn(Optional.of(drink));

        when(drinksRepository.save(any())).thenReturn(drink);

        DrinkDto dto=productsService.updateDrink(id,DtoConverter.convertDrinktoDrinkDto(drink));

        assertNotNull(dto);


        assertEquals(dto.getId(),drink.getId());
    }

    @Test
    public void  shouldNotUpdateDrink(){

        int id=1;
        Mockito.when(drinksRepository.findById(id)).thenReturn(Optional.empty());

        when(drinksRepository.save(any())).thenReturn(drink);

        DrinkDto dto=productsService.updateDrink(id,DtoConverter.convertDrinktoDrinkDto(drink));


        assertNull(dto);
    }

}