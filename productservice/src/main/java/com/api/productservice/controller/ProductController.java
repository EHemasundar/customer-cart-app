package com.api.productservice.controller;

import com.api.productservice.model.Product;
import com.api.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/products", method= RequestMethod.GET)
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/updateprice/")
    public ResponseEntity<String> updatePrice(@RequestBody List<Product> products) {
        List<Product> productList = productService.getAllProducts();
        try {
            productService.updatedProducts(products);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
    }
}
