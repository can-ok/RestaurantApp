package com.resturant.restapi.service;

import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.builder.ProductBuilder;
import com.resturant.restapi.Model.Product;

import com.resturant.restapi.builder.ProductCategoryBuilder;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.ProductDtoConverter;
import com.resturant.restapi.converter.ProductMapper;
import com.resturant.restapi.converter.ProductsCategoryMapper;
import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class ProductsServiceTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductsService productsService;

    @Mock
    ProductMapper productMapper;

    @Mock
    ProductsCategoryMapper productsCategoryMapper;


    @Mock
    private MessageSourceExternalizer messageSourceExternalizer;

    List<Product> productList =new ArrayList<>();

    List<ProductCategory> productCategories=new ArrayList<>();

    private Product product;

    private ProductDto productDto;

    private Pageable pages=PageRequest.of(0,5);

    private List<ProductDto> productDtoList=new ArrayList<>();

    List<ProductCategory> listCategory=new ArrayList<>( );

    @BeforeEach
    public void setUp() throws Exception{

        Set<ProductCategory> setCategory=new HashSet<>( );
        setCategory.add(new ProductCategoryBuilder().id(1).name("deneme").build());
        listCategory.add(new ProductCategoryBuilder().id(1).name("deneme").build());


        product=new ProductBuilder().id(1).title("deneme").description("descript").price(10).productcategory(setCategory).build();

        productList.add(product);
        productDto= ProductDtoConverter.convertDrinktoDrinkDto(product);

        productDtoList.add(productDto);


        when(productMapper.toDto(any())).thenReturn(productDto);

    }


    @Test
    void shouldgetAllDrinks() {

        Page<Product> products=new PageImpl<>(productList);
        when(productRepository.findAllProducts(pages)).thenReturn(products);
        Page<ProductDto> result = productsService.getProducts(0, 5);

        assertNotNull(result);
        //assertEquals(productList.size(),result.getTotalElements());

    }


    @Test
    void shouldgetSpecificProducts() {

        Slice<Product> products=new SliceImpl<>(productList);
        when(productRepository.findProductByProductcategoryId(1,pages)).thenReturn(products);
        Slice<ProductDto> result = productsService.getSpecificCategory(1,0, 5);

        assertNotNull(result);
        //assertEquals(productList.size(),result.getTotalElements());

    }

    @Test
    void shouldFindDrinkById(){
        Integer id=1;
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ProductDto testDrink=productsService.getDrinkById(id);
        //assertEquals(testDrink,drink); farklı addressler

        assertEquals(testDrink.getId(), product.getId());

    }


//    @Test(
//    void shouldNotFindDrinkById(){
//        Integer id=5;
//        when(productRepository.findById(id)).thenReturn(Optional.empty());
//
//        ProductDto testDto=productsService.getDrinkById(id);
//
//        assertNull(testDto);
//
//    }


//    @Test()
//    void shouldNotgetSpecificCategory(){
//        //get empty list
//        Slice<Product> products=new SliceImpl<>(productList);
//        when(productRepository.findProductByProductcategoryId(1,pages)).thenReturn(products);
//        Slice<ProductDto> result = productsService.getSpecificCategory(1,-1, 5);
//
//        assertNotNull(result);
//        //assertEquals(productList.size(),result.getTotalElements());
//    }

//    @Test
//    void shouldgetSpecificCategory(){
//        //get empty list
//        when(productRepository.findProductByProductcategoryId(1)).thenReturn(productList);
//
//        //<Product> listProducts= productRepository.findProductByProductcategoryId(1);
//        List<ProductDto> listProducts=productsService.getSpecificCategory(1);
//
//        assertEquals(listProducts.size(),listProducts.size());
//    }




}