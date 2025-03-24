package com.example.springbootdemo.service;

import com.example.springbootdemo.dto.ProductQueryParams;
import com.example.springbootdemo.dto.ProductRequest;
import com.example.springbootdemo.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest ProductRequest);

    void updateProduct(Integer productId, ProductRequest ProductRequest);

    void deleteProductById(Integer productId);
}
