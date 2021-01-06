package com.resturant.restapi.controller;

import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;



    @GetMapping(path = "/")
    public Page<ProductDto> getAllDrinks(@RequestParam(value = "page") int page,
                                         @RequestParam(value = "size") int size){

        return productsService.getProducts(page,size);
    }


    @GetMapping(path="/getAll")
    public Slice<ProductDto> getAllProducts(@RequestParam(value = "page") int page,
                                                     @RequestParam(value = "size") int size){

        return productsService.getAllDrinks(page,size);

    }


    @PostMapping(path ="/add/drink")
    public String addDrink(@Valid @RequestBody ProductDto productDTO){


        return productsService.insertDrink(productDTO);
    }

    @GetMapping(path = "/drink/{id}")
    public ProductDto retriveProduct(@NotNull @PathVariable Integer id){

        return productsService.getDrinkById(id);
    }


    @DeleteMapping(path = "/delete/drink/{id}")
    public String deleteDrink(@NotNull @PathVariable Integer id){

        return productsService.deleteDrink(id);
    }


    @PutMapping(path = "/update/drink/")
    public ProductDto updateDrink(@Valid @RequestBody ProductDto productDto){

        return productsService.updateDrink(productDto);
    }

    @GetMapping(path="/category/{id}/")
    public Slice<ProductDto> retrivebyProductCategor(@PathVariable int id, @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "size") int size){

        return productsService.getSpecificCategory(id,page,size);
    }



}
