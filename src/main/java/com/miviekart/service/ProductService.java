package com.miviekart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miviekart.dao.IProductRepository;
import com.miviekart.dto.ProductData;
import com.miviekart.exception.IdNotFoundException;
import com.miviekart.model.Product;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    private Product getProductEntity(ProductData productData) {
        Product product = new Product();
        product.setProductId(productData.getProductId());
        product.setProductName(productData.getProductName());
        product.setProductQuantity(productData.getProductQuantity());
        product.setProductPrice(productData.getProductPrice());
        product.setImageUrl(productData.getImageUrl());
        return product;
    }

    private ProductData getProductData(Product product) {
        ProductData productData = new ProductData();
        productData.setProductId(product.getProductId());
        productData.setProductName(product.getProductName());
        productData.setProductQuantity(product.getProductQuantity());
        productData.setProductPrice(product.getProductPrice());
        productData.setImageUrl(product.getImageUrl());
        return productData;
    }

    @Override
    public List<ProductData> findAll() {
        List<ProductData> productDataList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            productDataList.add(getProductData(product));
        });
        return productDataList;
    }

    @Override
    public ProductData findById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional == null) {
            new IdNotFoundException("product not found");

        }
        return getProductData(productOptional.get());
    }

    @Override
    public ProductData create(ProductData productData) {
        Product product = getProductEntity(productData);
        return getProductData(productRepository.save(product));
    }

    @Override
    public boolean delete(int id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            productRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public ProductData update(ProductData productData, int id) {
        Product product1 = productRepository.findById(id).get();
        if (product1 != null) {
            product1.setProductName(productData.getProductName());
            product1.setProductQuantity(productData.getProductQuantity());
            product1.setProductPrice(productData.getProductPrice());
            product1.setImageUrl(productData.getImageUrl());
            productRepository.save(product1);
            return getProductData(product1);
        }
        return null;
    }

}
