package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.Order;
import com.example.springbootdemo.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    void creatOrderItems(Integer userId, List<OrderItem> orderItemList);
}
