package com.api.productservice.service;

import com.api.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> productsList = new ArrayList<>();

    public ProductService() {
        productsList.add(new Product(10,"X", 500));
        productsList.add(new Product(11,"Y", 400));
        productsList.add(new Product(12,"Z", 300));
    }

    public List<Product> getAllProducts() {
        return updatedProducts(productsList);
    }

    // update products
    public List<Product> updatedProducts(List<Product> products) {
        products.forEach(product ->
                productsList.stream()
                        .filter(p -> p.getProductId() == product.getProductId())
                        .findFirst()
                        .ifPresent(p -> p.setProductPrice(product.getProductPrice())));

        return productsList;
    }
}
