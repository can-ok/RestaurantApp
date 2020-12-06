package com.resturant.restapi.service;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.ProductDtoConverter;
import com.resturant.restapi.dto.DrinkDto;
import com.resturant.restapi.dto.FoodDto;
import com.resturant.restapi.dto.ProductDto;
import com.resturant.restapi.repository.DrinksRepository;
import com.resturant.restapi.repository.FoodRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductsService {

    @Autowired
    DrinksRepository drinksRepository;

    @Autowired
    ProductCategoryRepository productcategoryRepository;

    @Autowired
    FoodRepository foodRepository;

    public List<DrinkDto> getAllDrinks(){

        List<DrinkDto> dtoDrinkList=(List<DrinkDto>)(List<?>) drinksRepository.findAll();

        return dtoDrinkList;
    }



    public List<? extends ProductDto> getSpecificCategory(int ProductCategoryId){

        List<Food> listfood=foodRepository.findFoodByProductcategoryId(ProductCategoryId);
        List<Drink> listdrink=drinksRepository.findDrinkByProductcategoryId(ProductCategoryId);

        List<FoodDto> foodDtoList=new ArrayList<>();
        List<DrinkDto> drinkDtoList=new ArrayList<>();

        if(!listfood.isEmpty())
        {

            return ProductDtoConverter.convertFoodListtoDtoList(foodDtoList,listfood);
        }
        else if(!listdrink.isEmpty())
        {
            return ProductDtoConverter.convertDrinkListToDrinDtoList(drinkDtoList,listdrink);
        }
        else {

            return Collections.emptyList();
        }
        
    }


    public List<FoodDto> getAllFoods(){

        List<FoodDto> foodDtoList=new ArrayList<>();


        foodDtoList= ProductDtoConverter.convertFoodListtoDtoList(foodDtoList,foodRepository.findAll());


        return foodDtoList;
    }

    public String insertFood(FoodDto dtoFood,int id){

        Optional<ProductCategory> productcategory=productcategoryRepository.findById(id);

        if(productcategory.isPresent()){

            Food food= ProductDtoConverter.convertFoodDtoToFood(dtoFood,productcategory);
            foodRepository.save(food);
            return "Success";
        }

        return "fail";
    }

    public String insertDrink(DrinkDto drinkDto, int id){
        Optional<ProductCategory> productcategory=productcategoryRepository.findById(id);

        if(productcategory.isPresent()){

            Drink drink= ProductDtoConverter.convertDrinkDtoToDrink(drinkDto,productcategory);
            drinksRepository.save(drink);
            return "Success";
        }

        return "fail";
    }

    public FoodDto getFoodById(Integer id){
        Optional<Food> optinalEntity=foodRepository.findById(id);

        if(!optinalEntity.isPresent()){

            return null;
        }
        else{

            FoodDto foodDto= ProductDtoConverter.convertFoodtoFoodDto(optinalEntity.get());
            return foodDto;
        }

    }


    public DrinkDto getDrinkById(Integer id){
        Optional<Drink> optinalEntity=drinksRepository.findById(id);

        if(!optinalEntity.isPresent()){

            return null;
        }

        else{

            DrinkDto drinkDto= ProductDtoConverter.convertDrinktoDrinkDto(optinalEntity.get());
            return drinkDto;
        }
    }


    public List<FoodDto> deleteFood(Integer id){

        List<FoodDto> foods=new ArrayList<>();

        Optional<Food> foodEntity=foodRepository.findById(id);
        if(foodEntity.isPresent()){

            foodEntity.get().setProductcategory(null);

            foodRepository.delete(foodEntity.get());
            foods=getAllFoods();

        }

        return foods;


    }

    public List<DrinkDto> deleteDrink(Integer id){

        List<DrinkDto> drinks=new ArrayList<>();

        Optional<Drink> drinkEntity=drinksRepository.findById(id);
        if(drinkEntity.isPresent()){

            drinkEntity.get().setProductcategory(null);

            drinksRepository.delete(drinkEntity.get());
            drinks=getAllDrinks();

        }

        return drinks;



    }


    public FoodDto updateFood(Integer id,FoodDto foodDto){
        Optional<Food> entity = foodRepository.findById(id);

        if (!entity.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;
        }
        else{

            entity.get().setId(id);
            entity.get().setDescription(foodDto.getDescription());
            entity.get().setTitle(foodDto.getTitle());
            //entity.get().setProductCategory(food.getProductCategory());
            entity.get().setPrice(foodDto.getPrice());
            foodRepository.save(entity.get());

            return foodDto;
        }
    }

    public DrinkDto updateDrink(Integer id,DrinkDto drinkDto){
        Optional<Drink> optinalDrink = drinksRepository.findById(id);

        if (!optinalDrink.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;}
        else{

            optinalDrink.get().setId(id);
            optinalDrink.get().setDescription(drinkDto.getDescription());
            optinalDrink.get().setPrice(drinkDto.getPrice());
            optinalDrink.get().setTitle(drinkDto.getTitle());
            //optinalDrink.get().setProductCategory(drink.getProductCategory());

            drinksRepository.save(optinalDrink.get());

            return drinkDto;
        }
    }



}
