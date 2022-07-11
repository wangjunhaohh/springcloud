package com.wjh;


import com.wjh.balancer.CustomLoadBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@LoadBalancerClient(name = "springcloud-provide-dept",configuration = CustomLoadBalancerConfiguration.class)
public class DeptConsumer_8002 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_8002.class,args);
    }
}
