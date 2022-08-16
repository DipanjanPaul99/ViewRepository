package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {

    @Query("DELETE Customer c WHERE c.id = ?1")
    void deleteByIdWithJPQL(int id);
}
