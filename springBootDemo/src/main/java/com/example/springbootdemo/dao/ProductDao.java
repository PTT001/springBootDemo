package com.example.springbootdemo.dao;

import com.example.springbootdemo.dto.ProductQueryParams;
import com.example.springbootdemo.dto.ProductRequest;
import com.example.springbootdemo.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest ProductRequest);

    void updateProduct(Integer productId, ProductRequest ProductRequest);

    void deleteProductById(Integer productId);
}
