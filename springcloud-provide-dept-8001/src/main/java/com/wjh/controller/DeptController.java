package com.wjh.controller;


import com.wjh.pojo.Dept;
import com.wjh.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//提供Restful服务
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
    public Dept get(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
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
