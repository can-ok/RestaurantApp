package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media,Integer> {
}
