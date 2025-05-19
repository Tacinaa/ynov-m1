package org.example.springsecurityproduct.service;

import org.example.springsecurityproduct.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product saveProduct(Product product);

    public List<Product> getAllProducts();

    public Optional<Product> getProductById(Long id);

    public Product updateProduct(Product product);

    public void deleteProduct(Long id);
}


