package com.bingye.feign.config;

import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class CustomFeignClientsConfiguration {

    /**
     * 日志级别
     * @return
     */
    //@Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}