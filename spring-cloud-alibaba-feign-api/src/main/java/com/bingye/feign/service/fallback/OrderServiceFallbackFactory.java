package com.bingye.feign.service.fallback;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.bingye.feign.service.IOrderService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderServiceFallbackFactory implements FallbackFactory<IOrderService> {

    @Override
    public IOrderService create(Throwable t) {

        if(t instanceof FlowException) {
            //流量控制异常
        }else if(t instanceof DegradeException) {
            //熔断控制异常
        }else if(t instanceof AuthorityException) {

        }else if(t instanceof ParamFlowException) {

        }
        return new IOrderService() {
            @Override
            public String getOrderById(String oid) {
                log.error("{}","进入到reduceProductById容错信息的error打印");
                log.error("{}",t);
                return "getOrderById熔断异常了："+oid;
            }
        };
    }
}
