package com.resturant.restapi.service;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.DrinkDto;
import com.resturant.restapi.repository.DrinksRepository;
import com.resturant.restapi.repository.FoodRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class ProductsServiceTest {


    @Mock
    private DrinksRepository drinksRepository;

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private ProductsService productsService;



    private List<Drink> drinkList=new ArrayList<>();

    private Drink drink=new Drink();

    @BeforeEach
    public void setUp() throws Exception{
        drink.setTitle("deneme");
        drink.setId(1);
        drink.setTitle("title");
        drink.setPrice(10);
        drink.setDescription("adsad");
        drink.setProductcategory(new ProductCategory(1,"dneme"));
        // add list
        drinkList.add(drink);

    }


    @Test
    void shouldgetAllDrinks() {

        when(drinksRepository.findAll()).thenReturn(drinkList);

        List<DrinkDto> resultList=productsService.getAllDrinks();
        assertEquals(drinkList,resultList);

    }

    @Test
    void shouldFindDrinkById(){
        Integer id=1;
        when(drinksRepository.findById(id)).thenReturn(Optional.of(drink));

        DrinkDto testDrink=productsService.getDrinkById(id);
        //assertEquals(testDrink,drink); farklı addressler

        assertEquals(testDrink.getId(),drink.getId());

    }

    @Test
    void shouldNotFindDrinkById(){
        Integer id=5;
        when(drinksRepository.findById(id)).thenReturn(Optional.empty());

        DrinkDto testDto=productsService.getDrinkById(id);

        assertNull(testDto);

    }


    @Test
    void shouldgetSpecificCategory(){
        //get empty list
        when(drinksRepository.findDrinkByProductcategoryId(1)).thenReturn(Collections.emptyList());

        List<? extends Product> listProducts= drinksRepository.findDrinkByProductcategoryId(1);

        assertEquals(listProducts.size(),0);
    }


}