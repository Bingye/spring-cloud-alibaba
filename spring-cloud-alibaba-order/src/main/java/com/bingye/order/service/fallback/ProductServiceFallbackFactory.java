/*
* Copyright @ 2021 bingye
* spring-cloud-alibaba-order 下午2:28:56
* All right reserved.
*
*/
package com.bingye.order.service.fallback;

import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.bingye.order.service.IProductService;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
* @desc: 服务熔断工厂类
* @author: bingye
* @createTime: 2021年2月21日 下午2:28:56
* @history:
* @version: v1.0
*/
@Slf4j
@Component
public class ProductServiceFallbackFactory implements FallbackFactory<IProductService>{

	/*
	*(non-Javadoc)
	* @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
	*/
	@Override
	public IProductService create(Throwable t) {
		if(t instanceof FlowException) {
			//流量控制异常
		}else if(t instanceof DegradeException) {
			//熔断控制异常
		}else if(t instanceof AuthorityException) {
			
		}else if(t instanceof ParamFlowException) {
			
		}
		return new IProductService() {
			
			@Override
			public String reduceProductById(String pid) {
				log.error("{}","进入到reduceProductById容错信息的error打印");
				log.error("{}",t);
				
				return "reduceProductById熔断异常了："+pid;
			}
			
			@Override
			public String getProductById(String pid) {
				log.error("{}","进入到getProductById容错信息的error打印");
				log.error("{}",t);
				return "getProductById熔断异常："+pid;
			}
		};
	}


}
