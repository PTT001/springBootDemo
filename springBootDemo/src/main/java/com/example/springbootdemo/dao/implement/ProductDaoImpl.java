package com.example.springbootdemo.dao.implement;

import com.example.springbootdemo.dao.ProductDao;
import com.example.springbootdemo.dto.ProductQueryParams;
import com.example.springbootdemo.dto.ProductRequest;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProduct(ProductQueryParams productQueryParams) {
        String sql = "select product_id, product_name,category,image_url,price,stock,description," +
                "created_date,last_modified_date From product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        if (productQueryParams.getCategory() != null) {
            sql += " and category=:category";
            map.put("category", productQueryParams.getCategory().name());
        }

        if (productQueryParams.getSearch() != null) {
            sql += " and product_name like :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        sql = sql + " order by " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        sql += " limit :limit offset :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
    }

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

    @Override
    public Integer createProduct(ProductRequest ProductRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock," +
                "description, created_date, last_modified_date)" +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description," +
                ":createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", ProductRequest.getProductName());
        map.put("category", ProductRequest.getCategory().toString());
        map.put("imageUrl", ProductRequest.getImageUrl());
        map.put("price", ProductRequest.getPrice());
        map.put("stock", ProductRequest.getStock());
        map.put("description", ProductRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest ProductRequest) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl, " +
                "price = :price, stock= :stock,description= :description, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", ProductRequest.getProductName());
        map.put("category", ProductRequest.getCategory().toString());
        map.put("imageUrl", ProductRequest.getImageUrl());
        map.put("price", ProductRequest.getPrice());
        map.put("stock", ProductRequest.getStock());
        map.put("description", ProductRequest.getDescription());
        map.put("lastModifiedDate", new Date());
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
