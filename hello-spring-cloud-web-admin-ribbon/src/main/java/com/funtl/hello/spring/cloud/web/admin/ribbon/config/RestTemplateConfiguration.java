package com.funtl.hello.spring.cloud.web.admin.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * 开启负载均衡功能
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    @LoadBalanced//开启负载均衡功能
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}