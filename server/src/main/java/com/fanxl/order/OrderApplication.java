package com.fanxl.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description SpringCloudApplication可以代替注释的三个注解
 * @author: fanxl
 * @date: 2018/10/26 0026 11:31
 */
@EnableFeignClients(basePackages = "com.fanxl.product.client")
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
