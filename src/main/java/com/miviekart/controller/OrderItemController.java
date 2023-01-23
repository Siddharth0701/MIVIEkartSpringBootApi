package com.miviekart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miviekart.dto.OrderItemData;
import com.miviekart.service.OrderItemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/orderItems", produces = "application/json")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemData>> getAllOrderItems() {
        try {
            List<OrderItemData> list = this.orderItemService.findAll();
            if (list.size() <= 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<OrderItemData> getProductById(@PathVariable("id") int id) {
        OrderItemData orderItemData = this.orderItemService.findById(id);
        if (orderItemData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.of(Optional.of(orderItemData));
    }

    @PostMapping
    public ResponseEntity<OrderItemData> addProduct(@RequestBody OrderItemData orderItemData) {
        OrderItemData p = null;
        try {

            p = this.orderItemService.create(orderItemData);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteOrderItem(@PathVariable("id") int id) {
        try {
            this.orderItemService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderItemData> updateOrderItem(@RequestBody OrderItemData orderItemData,
            @PathVariable("id") int id) {
        try {
            this.orderItemService.update(orderItemData, id);
            return ResponseEntity.ok().body(orderItemData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
