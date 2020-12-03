package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.dto.DrinkDto;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.dto.FoodDto;
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
    public List<DrinkDto> getAllDrinks(){

        return productsService.getAllDrinks();
    }

    @GetMapping(path="/getAll")
    private List<? extends ProductDto> getAllProducts(){

        return new ArrayList<ProductDto>() {{
            addAll(productsService.getAllDrinks());
            addAll(productsService.getAllFoods());
        }};
    }

    @GetMapping(path = "/food")
    public List<FoodDto> getAllFoods(){

        return productsService.getAllFoods();
    }

    @PostMapping(path ="/add/food/{categoryId}")
    public String addFood(@RequestBody FoodDto foodDto,@PathVariable int categoryId){

        return productsService.insertFood(foodDto,categoryId);
    }


    @PostMapping(path ="/add/drink/{categoryId}")
    public String addDrink(@RequestBody DrinkDto drinkDTO, @PathVariable int categoryId){

        return productsService.insertDrink(drinkDTO,categoryId);
    }

    @GetMapping(path = "/drink/{id}")
    public DrinkDto retriveDrink(@PathVariable Integer id){

        return productsService.getDrinkById(id);
    }

    @GetMapping(path = "/food/{id}")
    public FoodDto retriveFood(@PathVariable Integer id){

        return productsService.getFoodById(id);
    }

    @DeleteMapping(path = "/delete/food/{id}")
    public List<FoodDto> deleteFood(@PathVariable Integer id){

        return productsService.deleteFood(id);
    }

    @DeleteMapping(path = "/delete/drink/{id}")
    public List<DrinkDto> deleteDrink(@PathVariable Integer id){

        return productsService.deleteDrink(id);
    }

    @PutMapping(path = "/update/food/{id}")
    public FoodDto updateFood(@RequestBody FoodDto foodDto,@PathVariable Integer id){

        return productsService.updateFood(id,foodDto);
    }

    @PutMapping(path = "/update/drink/{id}")
    public DrinkDto updateDrink(@RequestBody DrinkDto drinkDto,@PathVariable Integer id){

        return productsService.updateDrink(id,drinkDto);
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
