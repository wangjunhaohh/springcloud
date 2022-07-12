package com.wjh.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wjh.pojo.Dept;
import com.wjh.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//提供Restful服务
//@DefaultProperties(defaultFallback = "fallBack",commandProperties = {// 开启断路器
//        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//        // 请求次数
//        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//        // 时间窗口期
//        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//        // 失败率
//        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")

    public boolean addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "getHystrix")
//    @HystrixCommand
    public Dept get(@PathVariable("id") Long id){
        if(deptService.queryById(id)==null){
            throw new RuntimeException();
        }
        return deptService.queryById(id);
    }

    public Dept getHystrix(@PathVariable("id") Long id){
        Dept dept = new Dept();
        dept.setDname("id不存在");
        dept.setDeptno(id);
        dept.setDb_source("id不存在");
        return dept;
    }

    public Dept fallBack(){
        Dept dept = new Dept();
        dept.setDname("统一熔断");
        return dept;
    }

//
    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
//        throw new RuntimeException();
        return deptService.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery(){
        List<String> services = client.getServices();
        System.out.println(services);
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDE-DEPT");
        for (ServiceInstance instance:instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                    instance.getServiceId()+"\t"+
                    instance.getPort()+"\t"+
                    instance.getUri()
            );
        }
        return this.client;
    }
}
