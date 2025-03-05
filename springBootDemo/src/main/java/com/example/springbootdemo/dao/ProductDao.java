package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
