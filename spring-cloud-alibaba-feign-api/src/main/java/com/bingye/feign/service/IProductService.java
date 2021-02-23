/*
* Copyright @ 2021 bingye
* spring-cloud-alibaba-order 下午12:30:15
* All right reserved.
*
*/
package com.bingye.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bingye.feign.config.CustomFeignClientsConfiguration;
import com.bingye.feign.service.fallback.ProductServiceFallbackFactory;

/**
* @desc: spring-cloud-alibaba-order
* @author: bingye
* @createTime: 2021年2月5日 下午12:30:15
* @history:
* @version: v1.0
*
* 	name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
	url: url一般用于调试，可以手动指定@FeignClient调用的地址
	decode404:当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException
	configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
	fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
	allbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码
	path: 定义当前FeignClient的统一前缀
	直接调用服务
	@FeignClient(
			name="product",
			path="spring-cloud-alibaba-product/product",
			url="localhost:9000"
	)

resource：资源名，即限流规则的作用对象
limitApp：流控针对的调用来源，若为default则不区分调用来源
grade：限流阀值类型（QPS或并发线程数）；0代表根据并发线程数量来限流，1代表根据QPS来进行流量控制
count：限流阀值
strategy：调用关系限流策略
controlBehavior：流量控制效果（直接拒绝、Warm Up、匀速排队）
clusterMode：是否集群模式
*/

//注册中心服务
//@FeignClient(
//	value="spring-cloud-alibaba-product",
//	path="spring-cloud-alibaba-product/product",
//	fallbackFactory = ProductServiceFallbackFactory.class
//)

//gateway
@FeignClient(
		value="spring-cloud-alibaba-gateway",
		path="spring-cloud-alibaba-product/product",
		configuration = { CustomFeignClientsConfiguration.class },
		fallbackFactory = ProductServiceFallbackFactory.class
)
public interface IProductService {

	@GetMapping("/get/{pid}")
	String getProductById(@PathVariable("pid") String pid);

	@GetMapping("/reduce/{pid}")
	String reduceProductById(@PathVariable("pid") String pid);

}
