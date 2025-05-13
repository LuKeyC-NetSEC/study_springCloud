package com.lyc.order.controller;


import com.lyc.order.bean.Order;
import com.lyc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userid,
                             @RequestParam("productId")Long productId){
        return orderService.createOrder(productId,userid);
    }
}
