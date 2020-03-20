    package com.funtl.hello.spring.cloud.web.admin.ribbon;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
    import org.springframework.cloud.netflix.hystrix.EnableHystrix;

    @SpringBootApplication
    @EnableDiscoveryClient   //服务消费者注解
    @EnableHystrix //熔断器hystrix
    public class WebAdminRibbonApplication {
        public static void main(String[] args) {
            SpringApplication.run(WebAdminRibbonApplication.class, args);
        }
    }