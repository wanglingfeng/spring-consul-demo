package com.joseph.consul.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lfwang on 2016/11/14.
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ClientApplication {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    /**
     * 从所有服务中选择一个服务(轮询)
     * @return
     */
    @RequestMapping("/discover")
    public String discover() {
        return loadBalancer.choose("consul-service").getUri().toString();
    }

    /**
     * 获取所有服务
     * @return
     */
    @RequestMapping("services")
    public List<String> services() {
        List<ServiceInstance> list = discoveryClient.getInstances("consul-service");

        if (null == list) return null;

        return list.stream().map(serviceInstance -> {
            System.out.println(serviceInstance);

            return serviceInstance.getUri().toString();
        }).collect(Collectors.toList());
    }
}
