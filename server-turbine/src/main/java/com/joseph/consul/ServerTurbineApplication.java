package com.joseph.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lfwang on 2016/11/16.
 */
@EnableTurbine
@EnableHystrixDashboard
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServerTurbineApplication {

    public static void main(String... args) {
        SpringApplication.run(ServerTurbineApplication.class, args);
    }

    @RequestMapping("/")
    public String hello() {
        return "PPAP";
    }
}
