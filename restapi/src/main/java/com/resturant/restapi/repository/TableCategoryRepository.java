package com.resturant.restapi.repository;

import com.resturant.restapi.Model.TableCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableCategoryRepository extends JpaRepository<TableCategory,Integer> {
}
