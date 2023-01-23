package com.miviekart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miviekart.dao.IOrderItemRepositoty;
import com.miviekart.dto.OrderItemData;
import com.miviekart.exception.IdNotFoundException;
import com.miviekart.model.OrderItem;

@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private IOrderItemRepositoty orderItemRepository;

    private OrderItem getOrderItemEntity(OrderItemData orderItemData) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(orderItemData.getItemId());
        orderItem.setProduct(orderItemData.getProduct());
        return orderItem;
    }

    private OrderItemData getOrderItemData(OrderItem orderItem) {
        OrderItemData orderItemData = new OrderItemData();
        orderItemData.setItemId(orderItem.getItemId());
        orderItemData.setProduct(orderItem.getProduct());
        return orderItemData;

    }

    @Override
    public List<OrderItemData> findAll() {
        List<OrderItemData> orderItemList = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findAll();
        orderItems.forEach(orderItem -> {
            orderItemList.add(getOrderItemData(orderItem));

        });
        return orderItemList;
    }

    @Override
    public OrderItemData findById(int id) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional == null) {
            new IdNotFoundException("OrderItem not found");

        }
        return getOrderItemData(orderItemOptional.get());
    }

    @Override
    public OrderItemData create(OrderItemData orderItemData) {

        OrderItem orderItem = getOrderItemEntity(orderItemData);
        return getOrderItemData(orderItemRepository.save(orderItem));

    }

    @Override
    public boolean delete(int id) {
        // boolean test = findAll().remove(findById(id));
        // orderItemRepository.deleteById(id);
        // return test;
        OrderItem orderItem = orderItemRepository.findById(id).get();
        if (orderItem != null) {
            orderItemRepository.deleteById(id);
            return true;

        }
        return false;
    }

    @Override
    public OrderItemData update(OrderItemData orderItemData, int id) {
        OrderItem orderItem = orderItemRepository.findById(id).get();
        if (orderItem != null) {
            orderItem.setProduct(orderItemData.getProduct());
            orderItemRepository.save(orderItem);
            return getOrderItemData(orderItem);

        }
        return null;
    }

}
