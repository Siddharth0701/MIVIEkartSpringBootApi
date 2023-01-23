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

import com.miviekart.dto.ProductData;
import com.miviekart.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/products", produces = "application/json")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductData>> getProducts() {
        try {
            List<ProductData> list = this.productService.findAll();
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
    public ResponseEntity<ProductData> getProduct(@PathVariable("id") int id) {
        ProductData product = this.productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.of(Optional.of(product));
    }

    @PostMapping
    public ResponseEntity<ProductData> addProduct(@RequestBody ProductData productData) {
        ProductData p = null;
        try {

            p = this.productService.create(productData);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") int productId) {
        try {
            this.productService.delete(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{productId}")
    public ResponseEntity<ProductData> updateProduct(@RequestBody ProductData product,
            @PathVariable("productId") int productId) {
        try {
            this.productService.update(product, productId);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



}
