package com.resturant.restapi.controller;


import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping( path = "/category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService categoryService;

    @GetMapping("/getAll")
    public List<ProductCategory> getAllCategories(){

        return categoryService.getAll();
    }

    @PostMapping("/save")
    public ProductCategory getAllCategories(@RequestBody ProductCategory productcategory){

        return categoryService.insertCatagory(productcategory);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id){

        return categoryService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public ProductCategory updateCategory(@PathVariable int id,@RequestBody ProductCategory category)
    {

        return categoryService.updateCategory(id,category);
    }


}
