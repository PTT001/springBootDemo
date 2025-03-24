package com.example.springbootdemo.service.implement;

import com.example.springbootdemo.dao.ProductDao;
import com.example.springbootdemo.dto.ProductQueryParams;
import com.example.springbootdemo.dto.ProductRequest;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProduct(ProductQueryParams productQueryParams) {
        return productDao.getProduct(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest ProductRequest) {
        return productDao.createProduct(ProductRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest ProductRequest) {
        productDao.updateProduct(productId, ProductRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
