package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.addAll;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;



    @GetMapping(path = "/drinks")
    public List<Drink> getAllDrinks(){

        return productsService.getAllDrinks();
    }

    @GetMapping(path="/getAll")
    private List<? extends Product> getAllProducts(){

        return new ArrayList<Product>() {{
            addAll(productsService.getAllDrinks());
            addAll(productsService.getAllFoods());
        }};
    }

    @GetMapping(path = "/food")
    public List<? extends Product> getAllFoods(){

        return productsService.getAllFoods();
    }

    @PostMapping(path ="/add/food/{id}")
    public String addFood(@RequestBody Food food,@PathVariable int id){

        return productsService.insertFood(food,id);
    }


    @PostMapping(path ="/add/drink/{id}")
    public String addDrink(@RequestBody Drink drink,@PathVariable int id){

        return productsService.insertDrink(drink,id);
    }

    @GetMapping(path = "/drink/{id}")
    public Drink retriveDrink(@PathVariable Integer id){

        return productsService.getDrinkById(id);
    }

    @GetMapping(path = "/food/{id}")
    public Food retriveFood(@PathVariable Integer id){

        return productsService.getFoodById(id);
    }

    @DeleteMapping(path = "/delete/food/{id}")
    public List<Food> deleteFood(@PathVariable Integer id){

        return productsService.deleteFood(id);
    }

    @DeleteMapping(path = "/delete/drink/{id}")
    public List<Drink> deleteDrink(@PathVariable Integer id){

        return productsService.deleteDrink(id);
    }

    @PutMapping(path = "/update/food/{id}")
    public Food updateFood(@RequestBody Food food,@PathVariable Integer id){

        return productsService.updateFood(id,food);
    }

    @PutMapping(path = "/update/drink/{id}")
    public Drink updateDrink(@RequestBody Drink drink,@PathVariable Integer id){

        return productsService.updateDrink(id,drink);
    }

    @GetMapping(path="/category/{id}")
    public List<? extends Product> retrivebyProductCategor(@PathVariable int id){

        return productsService.getSpecificCategory(id);
    }

//    @GetMapping(path="/getCategories")
//    public List<String> retriveAllCategories()
//    {
//        return new ArrayList<String>() {{
//            addAll(productsService.getAllCategoriesDrink());
//            addAll(productsService.getAllCategoriesFood());
//        }};
//    }

}
