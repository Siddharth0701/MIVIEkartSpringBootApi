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

import com.miviekart.dto.CustomerData;
import com.miviekart.service.CustomerService;

@RestController
@RequestMapping("api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerData>> getCustomer() {
        try {
            List<CustomerData> list = this.customerService.findAll();
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
    public ResponseEntity<CustomerData> getProduct(@PathVariable("id") int id) {
        CustomerData customerData = this.customerService.findById(id);
        if (customerData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.of(Optional.of(customerData));
    }

    @PostMapping
    public ResponseEntity<CustomerData> addProduct(@RequestBody CustomerData customerData) {
        CustomerData p = null;
        try {

            p = this.customerService.create(customerData);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("customerId") int customerId) {
        try {
            this.customerService.delete(customerId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{customerId}")
    public ResponseEntity<CustomerData> updateProduct(@RequestBody CustomerData customerData,
            @PathVariable("customerId") int customerId) {
        try {
            this.customerService.update(customerData, customerId);
            return ResponseEntity.ok().body(customerData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
