package com.amazon.ecommerce.service;
import com.amazon.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.*;
        import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final Map<Long, Product> productMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public Product createProduct(Product product) {
        long id = idGenerator.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        Product existingProduct = productMap.get(id);
        if (existingProduct != null) {
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            return Optional.of(existingProduct);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Long id) {
        return productMap.remove(id) != null;
    }
}
