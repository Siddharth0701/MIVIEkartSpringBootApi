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

import com.miviekart.dto.OrdersData;
import com.miviekart.service.OrdersService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/order", produces = "application/json")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<OrdersData>> getProducts() {
        try {
            List<OrdersData> list = this.ordersService.findAll();
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
    public ResponseEntity<OrdersData> getProduct(@PathVariable("id") int id) {
        OrdersData product = this.ordersService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.of(Optional.of(product));
    }

    @PostMapping
    public ResponseEntity<OrdersData> addProduct(@RequestBody OrdersData ordersData) {
        OrdersData p = null;
        try {

            p = this.ordersService.create(ordersData);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") int productId) {
        try {
            this.ordersService.delete(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{productId}")
    public ResponseEntity<OrdersData> updateProduct(@RequestBody OrdersData product,
            @PathVariable("productId") int productId) {
        try {
            this.ordersService.update(product, productId);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
