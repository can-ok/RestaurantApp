package com.resturant.restapi.repository;


import com.resturant.restapi.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("Select c from Customer c where c.phoneNumber LIKE CONCAT('%',:phoneNumber,'%')")
    Page<Customer> findAllByPhoneNumber(Pageable pageable, String phoneNumber);




    //Page<Customer> findAllByPhoneNumberAndPhoneNumberContains(Pageable pagable);

}
