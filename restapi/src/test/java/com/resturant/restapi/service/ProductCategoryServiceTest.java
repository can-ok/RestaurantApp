package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.builder.MediaDtoBuilder;
import com.resturant.restapi.builder.ProductCategoryBuilder;
import com.resturant.restapi.builder.ProductCategoryDtoBuilder;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.converter.ProductsCategoryMapper;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryServiceTest {

    @Mock
    private ProductCategoryRepository productcategoryRepository;

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    ProductsCategoryMapper productsCategoryMapper;

    @Mock
    private MessageSourceExternalizer messageSourceExternalizer;

    @InjectMocks
    private ProductCategoryService productCategoryService;

    private List<ProductCategory> listCategory=new ArrayList<>( );
    private Set<ProductCategoryDto> setCategory=new HashSet<>( );
    private ProductCategory productCategory;
    private ProductCategoryDto productCategoryDto;

    private Media media;

    @Before
    public void setUp() throws Exception {
        productCategory=new ProductCategoryBuilder().id(1).name("deneme").description("x").build();
        listCategory.add(productCategory);
        productCategoryDto=new ProductCategoryDtoBuilder().id(1).name("deneme").description("x").build();
        setCategory.add(productCategoryDto);

        byte[] fileBytes = "deneme".getBytes();
        media= MediaDtoConverter.mediaDtoToMedia(new MediaDtoBuilder().fileContent(fileBytes).id(1).name("deneme").build());

        when(productsCategoryMapper.toDto(productCategory)).thenReturn(productCategoryDto);
        when(productsCategoryMapper.toEntity(productCategoryDto)).thenReturn(productCategory);
        when(productsCategoryMapper.toProductCategoryListDtoSet(listCategory)).thenReturn(setCategory);
    }

    @Test
    public void getAll() {
        when(productcategoryRepository.findAll()).thenReturn(listCategory);
        Set<ProductCategoryDto> all = productCategoryService.getAll();
        assertEquals(all.size(),listCategory.size());
        //
    }

    @Test
    public void insertCatagory() {
        int id=1;
        when(mediaRepository.findById(id)).thenReturn(Optional.of(media));
        Integer result=productCategoryService.insertCatagory(productCategoryDto).getId();
        assertEquals(result,productCategoryDto.getId());

    }

//    @Test(expected = ContentNotAllowed.class)
//    public void shouldNotInsertCatagoryWhenContentNotAllowed() {
//        int id=1;
//        when(mediaRepository.findById(id)).thenReturn(Optional.of(media));
//
//        assertEquals(productCategoryService.insertCatagory(null).getId(),productCategoryDto.getId());
//
//    }

    @Test(expected = EntityNotFound.class)
    public void shouldNotInsertCatagoryWhenEntityNotFound() {
        int id=1;
        when(mediaRepository.findById(id)).thenReturn(Optional.empty());

        assertEquals(productCategoryService.insertCatagory(productCategoryDto).getId(),productCategoryDto.getId());

    }

    @Test
    public void deleteUser() {
        when(productcategoryRepository.findById(1)).thenReturn(Optional.of(productCategory));

        assertEquals(productCategoryService.deleteUser(1),"Success");

    }

    @Test(expected = EntityNotFound.class)
    public void shoudNotDeleteUser() {
        when(productcategoryRepository.findById(1)).thenReturn(Optional.empty());
        assertEquals(productCategoryService.deleteUser(1),"Success");

    }

    @Test
    public void updateCategory() {
        when(productcategoryRepository.findById(1)).thenReturn(Optional.of(productCategory));
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));

        assertEquals(productCategoryService.updateCategory(1,productCategoryDto).getId(),productCategoryDto.getId());
    }

    @Test(expected = EntityNotFound.class)
    public void shouldNotUpdateCategory() {
        when(productcategoryRepository.findById(1)).thenReturn(Optional.empty());
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));

        assertEquals(productCategoryService.updateCategory(1,productCategoryDto).getId(),productCategoryDto.getId());
    }

    @Test(expected = EntityNotFound.class)
    public void notGetDrinkById() {
        when(productcategoryRepository.findById(1)).thenReturn(Optional.empty());

        assertEquals(productCategoryService.getDrinkById(1).getId(),productCategoryDto.getId());

    }

    @Test()
    public void GetDrinkById() {
        when(productcategoryRepository.findById(1)).thenReturn(Optional.of(productCategory));

        assertEquals(productCategoryService.getDrinkById(1).getId(),productCategoryDto.getId());

    }
}