package com.resturant.restapi.controller;

import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;



    @GetMapping(path = "/drinks")
    public List<ProductDto> getAllDrinks(){

        return productsService.getAllDrinks();
    }

    @GetMapping(path="/getAll")
    public List<? extends ProductDto> getAllProducts(){

        return new ArrayList<ProductDto>() {{
            addAll(productsService.getAllDrinks());
        }};
    }


    @PostMapping(path ="/add/drink")
    public String addDrink(@RequestBody ProductDto productDTO){


        return productsService.insertDrink(productDTO);
    }

    @GetMapping(path = "/drink/{id}")
    public ProductDto retriveDrink(@PathVariable Integer id){

        return productsService.getDrinkById(id);
    }


    @DeleteMapping(path = "/delete/drink/{id}")
    public List<ProductDto> deleteDrink(@PathVariable Integer id){

        return productsService.deleteDrink(id);
    }


    @PutMapping(path = "/update/drink/")
    public ProductDto updateDrink(@RequestBody ProductDto productDto){

        return productsService.updateDrink(productDto);
    }

    @GetMapping(path="/category/{id}")
    public List<ProductDto> retrivebyProductCategor(@PathVariable int id){

        return productsService.getSpecificCategory(id);
    }



}
