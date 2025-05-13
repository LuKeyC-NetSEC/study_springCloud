package com.lyc.order.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.lyc.order.bean.Order;
import com.lyc.order.service.OrderService;
import com.lyc.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Order createOrder(Long productID, Long userId) {
        Product product = getProductFromRemote(productID);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("LuKey_C");
        order.setAddress("lyc");
        order.setProductList(Arrays.asList(product));

        return order;
    }

    private Product getProductFromRemote(Long productId){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        //远程Url http://localhost:9000/product/999
        String url = "http://"+serviceInstance.getHost() + ":" + serviceInstance.getPort() +"/product/" + productId;
        log.info("远程请求路径：{}",url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
