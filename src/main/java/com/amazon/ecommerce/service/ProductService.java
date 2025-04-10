package com.amazon.ecommerce.service;

import com.amazon.ecommerce.entity.Product;
import com.amazon.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Optional<Product> updateProduct(String id, Product productDetails) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            existingProduct.get().setName(productDetails.getName());
            existingProduct.get().setDescription(productDetails.getDescription());
            existingProduct.get().setPrice(productDetails.getPrice());
            return Optional.of(productRepository.save(existingProduct.get()));

        }
        return Optional.empty();
    }

    public boolean deleteProduct(String id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
