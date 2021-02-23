package com.bingye.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableFeignClients //开启openfeign服务调用支持
@RibbonClients  //开启ribbon服务调用负载均衡支持
@EnableBinding(value= {Sink.class}) //spring cloud stream的配置和启动支持
public class OrderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
