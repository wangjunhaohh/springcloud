package com.wjh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication()
@EnableEurekaClient
public class DeptProvide_8003 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvide_8003.class,args);
    }
}
