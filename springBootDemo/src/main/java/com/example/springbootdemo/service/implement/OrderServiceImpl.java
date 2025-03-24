package com.example.springbootdemo.service.implement;

import com.example.springbootdemo.dao.OrderDao;
import com.example.springbootdemo.dao.ProductDao;
import com.example.springbootdemo.dto.BuyItem;
import com.example.springbootdemo.dto.CreateOrderRequest;
import com.example.springbootdemo.model.Order;
import com.example.springbootdemo.model.OrderItem;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {

            Product product = productDao.getProductById(buyItem.getProductId());

            int Amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += Amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(Amount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.creatOrderItems(orderId, orderItemList);

        return orderId;
    }
}
