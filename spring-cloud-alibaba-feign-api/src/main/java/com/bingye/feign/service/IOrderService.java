package com.bingye.feign.service;

import com.bingye.feign.config.CustomFeignClientsConfiguration;
import com.bingye.feign.service.fallback.OrderServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "spring-cloud-alibaba-order",
        path = "spring-cloud-alibaba-order/order",
        configuration = CustomFeignClientsConfiguration.class,
        fallbackFactory = OrderServiceFallbackFactory.class
)
public interface IOrderService {

    @GetMapping("/get/{oid}")
    public String getOrderById(@PathVariable("oid") String oid);

}
