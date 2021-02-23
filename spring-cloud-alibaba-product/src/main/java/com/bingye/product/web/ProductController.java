/*
* Copyright @ 2021 bingye
* spring-cloud-alibaba-product 下午12:04:37
* All right reserved.
*
*/
package com.bingye.product.web;

import com.bingye.feign.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

import javax.annotation.Resource;

/**
* @desc: spring-cloud-alibaba-product
* @author: bingye
* @createTime: 2021年2月5日 下午12:04:37
* @history:
* @version: v1.0
*/
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Value("${server.port}")
	public String serverPort;
	
	@Autowired
	private Source source;

	@Autowired
	public IOrderService orderService;

	/**
	* spring cloud openfeign 
	* @author: bingye
	* @createTime: 2021年2月20日 下午2:30:15
	* @history:
	* @param pid
	* @return String
	*/
	@GetMapping("/get/{pid}")
	@SentinelResource("getProductById")
	public String getProductById(@PathVariable("pid") String pid) {
		return "product : " + pid + " serverPort : "+serverPort;
	}
	
	/**
	* Spring cloud stream 减库存
	* @author: bingye
	* @createTime: 2021年2月19日 下午2:35:23
	* @history:
	* @param message void
	*/
	@GetMapping("/reduce/{pid}")
	@SendTo(value=Source.OUTPUT)
	public String sendMyMessage(@PathVariable("pid") String pid) {
//		try {
//			TimeUnit.MINUTES.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		Message<byte[]> message = MessageBuilder.withPayload(pid.getBytes()).build();
		source.output().send(message);
		return "产品减少成功："+pid;
	}

	/**
	 * 获取订单信息
	 * @param oid
	 * @return
	 */
	@GetMapping("/order/get/{oid}")
	public String getOrderById(@PathVariable("oid") String oid){
		return orderService.getOrderById(oid);
	}
}
