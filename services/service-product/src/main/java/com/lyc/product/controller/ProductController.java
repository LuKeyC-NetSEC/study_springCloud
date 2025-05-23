package com.lyc.product.controller;


import com.lyc.product.bean.Product;
import com.lyc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long productId){
        return productService.getProductById(productId);
    }
}
