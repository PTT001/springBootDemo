package com.example.springbootdemo.service;

import com.example.springbootdemo.dto.CreateOrderRequest;
import com.example.springbootdemo.model.Order;

public interface OrderService {

    Order getOrderById(Integer order);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
