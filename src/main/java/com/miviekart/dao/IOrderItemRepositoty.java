package com.miviekart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miviekart.model.OrderItem;

@Repository
public interface IOrderItemRepositoty extends JpaRepository<OrderItem, Integer> {

}
