package com.bingye.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication(scanBasePackages = "com.bingye")
@EnableFeignClients(basePackages = {"com.bingye.feign"})
@RibbonClients  //开启ribbon服务调用负载均衡支持
@EnableBinding(value= {Source.class})
public class ProductApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
