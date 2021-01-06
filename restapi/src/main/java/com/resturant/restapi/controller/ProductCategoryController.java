package com.resturant.restapi.controller;


import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping( path = "/category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService categoryService;

    @GetMapping("/getAll")
    public Set<ProductCategoryDto> getAllCategories(){

        return categoryService.getAll();
    }

    @PostMapping("/save")
    public ProductCategoryDto saveCategory(@Valid @RequestBody ProductCategoryDto productcategory){

        return categoryService.insertCatagory(productcategory);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@NotNull @PathVariable int id){

        return categoryService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public ProductCategoryDto updateCategory(@NotNull @PathVariable int id, @Valid @RequestBody ProductCategoryDto category)
    {
        return categoryService.updateCategory(id,category);
    }

    @GetMapping(path = "/get/{id}")
    public ProductCategoryDto retriveDrink(@NotNull @PathVariable Integer id){

        return categoryService.getDrinkById(id);
    }

}
