package com.wjh.balancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

public class CustomLoadBalancer implements ReactorServiceInstanceLoadBalancer {
    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    private int count=0;
    private int index=0;

    public CustomLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable();
        return supplier.get().next().map(this::getInstanceResponse);
    }

    /**
     * 使用随机数获取服务
     * @param instances
     * @return
     */
    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances){
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }
        count++;
        if(count>=4){
            count=0;
            index++;
        }
        // 随机算法
        int size = instances.size();
        if(index>=size){
            index=0;
        }
        ServiceInstance instance=instances.get(index);
        return new DefaultResponse(instance);
    }
}
