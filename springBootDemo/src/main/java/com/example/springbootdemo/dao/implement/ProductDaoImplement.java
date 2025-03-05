package com.example.springbootdemo.dao.implement;

import com.example.springbootdemo.dao.ProductDao;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImplement implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id,product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date " +
                "FROM product WHERE product_id = :produceId";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("produceId", productId);

        List<Product> ProductList = namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());

        if (!ProductList.isEmpty()) {
            return ProductList.get(0);
        } else {
            return null;
        }
    }
}
