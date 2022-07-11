package com.wjh.balancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomLoadBalancerConfiguration {


    @Bean
    public ReactorServiceInstanceLoadBalancer customLoad(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider){
        return new CustomLoadBalancer(serviceInstanceListSupplierProvider);
    }
}
