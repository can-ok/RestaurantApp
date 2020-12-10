//package com.resturant.restapi.service;
//
//import com.resturant.restapi.Model.ProductCategory;
//import com.resturant.restapi.builder.DrinkBuilder;
//import com.resturant.restapi.Model.Product;
//import com.resturant.restapi.dto.DrinkDto;
//import com.resturant.restapi.repository.ProductRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.*;
//
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.junit.Assert.*;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//class ProductsServiceTest {
//
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private FoodRepository foodRepository;
//
//    @InjectMocks
//    private ProductsService productsService;
//
//
//
//    List<Product> productList =new ArrayList<>();
//    private Product product;
//    @BeforeEach
//    public void setUp() throws Exception{
//
//        product =new DrinkBuilder().id(1).title("deneme")
//                .description("deneme").price(1).productcategory(new ProductCategory(1,"dneme"))
//        .build();
//
//        // add list
//        productList.add(product);
//
//    }
//
//
//    @Test
//    void shouldgetAllDrinks() {
//
//        when(productRepository.findAll()).thenReturn(productList);
//
//        List<DrinkDto> resultList=productsService.getAllDrinks();
//        assertEquals(productList,resultList);
//
//    }
//
//    @Test
//    void shouldFindDrinkById(){
//        Integer id=1;
//        when(productRepository.findById(id)).thenReturn(Optional.of(product));
//
//        DrinkDto testDrink=productsService.getDrinkById(id);
//        //assertEquals(testDrink,drink); farklı addressler
//
//        assertEquals(testDrink.getId(), product.getId());
//
//    }
//
//    @Test
//    void shouldNotFindDrinkById(){
//        Integer id=5;
//        when(productRepository.findById(id)).thenReturn(Optional.empty());
//
//        DrinkDto testDto=productsService.getDrinkById(id);
//
//        assertNull(testDto);
//
//    }
//
//
//    @Test
//    void shouldgetSpecificCategory(){
//        //get empty list
//        when(productRepository.findDrinkByProductcategoryId(1)).thenReturn(Collections.emptyList());
//
//        List<? extends Product> listProducts= productRepository.findDrinkByProductcategoryId(1);
//
//        assertEquals(listProducts.size(),0);
//    }
//
//
//}