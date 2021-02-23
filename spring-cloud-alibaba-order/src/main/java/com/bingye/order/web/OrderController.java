/*
* Copyright @ 2021 bingye
* spring-cloud-alibaba-order 上午11:26:53
* All right reserved.
*
*/
package com.bingye.order.web;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.bingye.feign.service.IProductService;

import lombok.extern.slf4j.Slf4j;

/**
* @desc: spring-cloud-alibaba-order
* @author: bingye
* @createTime: 2021年2月5日 上午11:26:53
* @history:
* @version: v1.0
*/
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	public IProductService productService;

	/**
	* 获取商品
	* @author: bingye
	* @createTime: 2021年2月20日 下午2:36:14
	* @history:
	* @param pid
	* @return String
	*/
	@GetMapping("/product/get/{pid}")
	@SentinelResource(value="getProductById")
	public String getProductById(@PathVariable("pid") String pid) {
		return productService.getProductById(pid);
	}

//	public String getProductById_BlockHandler(String pid,BlockException ex) {
//		return "getProductById_BlockHandler："+pid;
//	}
	
	public String getProductById_BlockException(String pid,Throwable t) {
		return "getProductById_BlockException："+pid;
	}
	
	/**
	* 减商品
	* @author: bingye
	* @createTime: 2021年2月20日 下午2:36:14
	* @history:
	* @param pid
	* @return String
	*/
	@GetMapping("/product/reduce/{pid}")
//	@SentinelResource(
//			value="reduceProductById"
//			//blockHandler="getProductById_BlockHandler",
//			//fallback="getProductById_BlockException"
//	)
	public String reduceProductById(@PathVariable("pid") String pid) {
		log.info("通知商品端减少库存： "+ pid);
		productService.reduceProductById(pid);
		return "成功："+pid;
	}
	
	/**
	* Spring cloud stream 库存减完通知消费者端
	* @author: bingye
	* @createTime: 2021年2月19日 下午2:35:23
	* @history:
	* @param message void
	*/
	@StreamListener(Sink.INPUT)
    public void processMyMessage(String message) {
        log.info("接收到消息：" + message);
    }

	/**
	 * 获取订单信息
	 */
	@GetMapping("/get/{oid}")
    public String getOrderById(@PathVariable("oid") String oid){
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "成功：oid"+ oid;
	}
}
