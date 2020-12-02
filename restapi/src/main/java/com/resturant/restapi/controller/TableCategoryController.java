//package com.resturant.restapi.controller;
//
//
//import com.resturant.restapi.Model.TableCategory;
//import com.resturant.restapi.service.TableCategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
//@RestController
//@RequestMapping("/tablecategory")
//public class TableCategoryController {
//
//    @Autowired
//    TableCategoryService tableCategoryService;
//
//    @GetMapping("/getAll")
//    public List<TableCategory> getAll(){
//
//        return tableCategoryService.getAll();
//    }
//
//    @GetMapping("/getTable/{id}")
//    public TableCategory getTable(@PathVariable int id){
//        return tableCategoryService.getTable(id);
//    }
//
//
//}
