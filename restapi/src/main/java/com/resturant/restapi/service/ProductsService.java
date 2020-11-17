package com.resturant.restapi.service;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.repository.DrinksRepository;
import com.resturant.restapi.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    DrinksRepository drinksRepository;

    @Autowired
    FoodRepository foodRepository;

    public List<Drink> getAllDrinks(){


        return drinksRepository.findAll();
    }


    public List<Food> getAllFoods(){

        return foodRepository.findAll();
    }

    public Food insertFood(Food food){
        Food foodEntity=foodRepository.save(food);
        return foodEntity;
    }

    public Drink insertDrink(Drink drink){
        Drink drinkEntity=drinksRepository.save(drink);
        return drinkEntity;
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

        foodRepository.deleteById(id);

        return foodRepository.findAll();
    }

    public List<Drink> deleteDrink(Integer id){

        drinksRepository.deleteById(id);

        return drinksRepository.findAll();
    }


    public Food updateFood(Integer id,Food food){
        Optional<Food> entity = foodRepository.findById(id);

        if (!entity.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;}
        else{

            entity.get().setId(id);
            entity.get().setDescription(food.getDescription());
            entity.get().setPrice(food.getPrice());
            entity.get().setTitle(food.getTitle());
            entity.get().setProductCategory(food.getProductCategory());

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
            optinalDrink.get().setProductCategory(drink.getProductCategory());

            drinksRepository.save(optinalDrink.get());

            return optinalDrink.get();
        }
    }

}
