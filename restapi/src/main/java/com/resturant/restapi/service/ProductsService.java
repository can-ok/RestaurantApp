package com.resturant.restapi.service;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.repository.DrinksRepository;
import com.resturant.restapi.repository.FoodRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    DrinksRepository drinksRepository;

    @Autowired
    ProductCategoryRepository productcategoryRepository;

    @Autowired
    FoodRepository foodRepository;

    public List<Drink> getAllDrinks(){

        return drinksRepository.findAll();
    }


//    public List<? extends Product> getSpecificCategory(String ProductCategor){
//
//        List<Food> listfood=foodRepository.getCategoryByFoods(ProductCategor);
//        List<Drink> listDrink=drinksRepository.findDrinkByProductCategory(ProductCategor);
//
//        if(!listfood.isEmpty())
//        {
//            return listfood;
//        }
//        else if(!listDrink.isEmpty())
//        {
//            return listDrink;
//        }
//        else {
//
//            return Collections.emptyList();
//        }
//
//    }


    public List<? extends Product> getSpecificCategory(int ProductCategoryId){

        List<Food> listfood=foodRepository.findFoodByProductcategoryId(ProductCategoryId);
        List<Drink> listdrink=drinksRepository.findDrinkByProductcategoryId(ProductCategoryId);

        if(!listfood.isEmpty())
        {
            return listfood;
        }
        else if(!listdrink.isEmpty())
        {
            return listdrink;
        }
        else {

            return Collections.emptyList();
        }
        
    }





//    public List<String> getAllCategoriesFood(){
//
//        return foodRepository.getCategories();
//    }
//
//    public List<String> getAllCategoriesDrink(){
//
//        return drinksRepository.getCategories();
//    }

    public List<Food> getAllFoods(){

        return foodRepository.findAll();
    }

    public String insertFood(Food food,int id){

        Optional<ProductCategory> productcategory=productcategoryRepository.findById(id);
        if(productcategory.isPresent()){

            food.setProductcategory(productcategory.get());
            foodRepository.save(food);
            return "Success";
        }

        return "fail";
    }

    public String insertDrink(Drink drink,int id){
        Optional<ProductCategory> productcategory=productcategoryRepository.findById(id);

        if(productcategory.isPresent()){

            drink.setProductcategory(productcategory.get());
            drinksRepository.save(drink);
            return "Success";
        }



        return "fail";
    }

    public Food getFoodById(Integer id){
        Optional<Food> optinalEntity=foodRepository.findById(id);

        if(!optinalEntity.isPresent()){

            return null;
        }

        return optinalEntity.get();
    }


    public Drink getDrinkById(Integer id){
        Optional<Drink> optinalEntity=drinksRepository.findById(id);

        if(!optinalEntity.isPresent()){

            return null;
        }

        return optinalEntity.get();
    }


    public List<Food> deleteFood(Integer id){

        List<Food> foods=new ArrayList<>();

        Optional<Food> foodEntity=foodRepository.findById(id);
        if(foodEntity.isPresent()){

            foodEntity.get().setProductcategory(null);

            foodRepository.delete(foodEntity.get());
            foods=foodRepository.findAll();

        }

        return foods;


    }

    public List<Drink> deleteDrink(Integer id){

        List<Drink> foods=new ArrayList<>();

        Optional<Drink> drinkEntity=drinksRepository.findById(id);
        if(drinkEntity.isPresent()){

            drinkEntity.get().setProductcategory(null);

            drinksRepository.delete(drinkEntity.get());
            foods=drinksRepository.findAll();

        }

        return foods;



    }


    public Food updateFood(Integer id,Food food){
        Optional<Food> entity = foodRepository.findById(id);

        if (!entity.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;}
        else{

            entity.get().setId(id);
            entity.get().setDescription(food.getDescription());
            entity.get().setTitle(food.getTitle());
            //entity.get().setProductCategory(food.getProductCategory());
            entity.get().setPrice(food.getPrice());
            foodRepository.save(entity.get());

            return entity.get();
        }
    }

    public Drink updateDrink(Integer id,Drink drink){
        Optional<Drink> optinalDrink = drinksRepository.findById(id);

        if (!optinalDrink.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;}
        else{

            optinalDrink.get().setId(id);
            optinalDrink.get().setDescription(drink.getDescription());
            optinalDrink.get().setPrice(drink.getPrice());
            optinalDrink.get().setTitle(drink.getTitle());
            //optinalDrink.get().setProductCategory(drink.getProductCategory());

            drinksRepository.save(optinalDrink.get());

            return optinalDrink.get();
        }
    }



}
