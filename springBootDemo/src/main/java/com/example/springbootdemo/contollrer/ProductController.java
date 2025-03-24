package com.example.springbootdemo.contollrer;

import com.example.springbootdemo.constant.ProductCategory;
import com.example.springbootdemo.dto.ProductQueryParams;
import com.example.springbootdemo.dto.ProductRequest;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(
            //查詢條件
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序
            @RequestParam(defaultValue = "created_Date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //分頁
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList = productService.getProduct(productQueryParams);

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(200).body(product);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequest ProductRequest) {
        Integer productId = productService.createProduct(ProductRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest ProductRequest) {

        Product product = productService.getProductById(productId);

        if (product == null) {
            return ResponseEntity.status(404).build();
        }

        productService.updateProduct(productId, ProductRequest);

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) {

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
