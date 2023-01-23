package com.miviekart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miviekart.model.Orders;


@Repository
public interface IOrdersRepository extends JpaRepository<Orders,Integer> {

}
