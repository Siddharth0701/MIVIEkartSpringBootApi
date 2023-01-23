package com.miviekart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miviekart.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    
}
