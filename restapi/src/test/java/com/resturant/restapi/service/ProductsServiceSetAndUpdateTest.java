
package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.builder.MediaDtoBuilder;
import com.resturant.restapi.builder.ProductBuilder;
import com.resturant.restapi.builder.ProductCategoryBuilder;
import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.converter.ProductDtoConverter;

import com.resturant.restapi.dto.ProductDto;

import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import com.resturant.restapi.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductsServiceSetAndUpdateTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private ProductsService productsService;



    private ProductCategory productCategory;

    List<Product> productList =new ArrayList<>();
    private Product product;

    private ProductDto productDto;

    private  Media media;


    @Before
    public void setUp() throws Exception{

        Set<ProductCategory> setCategory=new HashSet<>( );
        setCategory.add(new ProductCategoryBuilder().id(1).name("deneme").build());

        byte[] fileBytes = "deneme".getBytes();
       media= MediaDtoConverter.mediaDtoToMedia(new MediaDtoBuilder().fileContent(fileBytes).id(1).name("deneme").build());

        product=new ProductBuilder().id(1).title("deneme").description("descript").price(10).productcategory(setCategory).media(media).build();

        productList.add(product);

        productDto= ProductDtoConverter.convertDrinktoDrinkDto(product);

        productCategory=new ProductCategoryBuilder().id(1).description("deneme").name("deneme").build();

    }



    @Test
    public void shouldNotInsertFood() {
        // ın that scenario we assume that catagory doesnt exit so it goes fail

        Mockito.when(productRepository.save(any())).thenReturn(product);
        String response=productsService.insertDrink(ProductDtoConverter.convertDrinktoDrinkDto(product));


        assertNotNull(response);
        assertEquals(response,"Fail");
    }

    @Test
    public void insertProduct() {

        when(productCategoryRepository.findById(any())).thenReturn(Optional.of((productCategory)));
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));
        Mockito.when(productRepository.save(any())).thenReturn(product);

        String response=productsService.insertDrink(ProductDtoConverter.convertDrinktoDrinkDto(product));

        assertNotNull(response);
        assertEquals(response,"Success");
    }



    @Test
    public void shouldupdateDrink() {
        int id=1;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        when(productRepository.save(any())).thenReturn(product);
        when(productCategoryRepository.findById(any())).thenReturn(Optional.of(productCategory));
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));
        ProductDto dto=productsService.updateDrink(productDto);

        assertNotNull(dto);


        assertEquals(dto.getId(),product.getId());
    }

    @Test
    public void  shouldNotUpdateDrink(){

        int id=1;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        when(productRepository.save(any())).thenReturn(product);

        ProductDto dto=productsService.updateDrink(productDto);


        assertNull(dto);
    }

    @Test
    public void shouldNotDelete(){
        int id=1;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
        String response=productsService.deleteDrink(id);

        assertEquals(response,"Fail");
    }


    @Test
    public void shouldDelete(){
        int id=1;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        List<ProductDto> dtoList=new ArrayList<>();
        List<Product> prodList=Arrays.asList(product);
        //Mockito.when(productsService.getAllDrinks()).thenReturn(ProductDtoConverter.convertDrinkListToDrinDtoList(dtoList,prodList));
        //List<ProductDto> dto=productsService.deleteDrink(id);
        String result=productsService.deleteDrink(id);
        assertEquals(result,"Succes");
    }

}
