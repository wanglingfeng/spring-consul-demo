package com.joseph.basic.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.agent.model.Service;

import java.util.Map;

/**
 * Created by lfwang on 2016/11/14.
 */
public class ServiceApplication {

    static ConsulClient consul = new ConsulClient("localhost");

    public static void main(String[] args) {
        serviceRegister();
        serviceGet();
    }

    public static void serviceRegister() {
        //注册新的包含指定健康检查设置的服务
        NewService newService = new NewService();
        newService.setId("basic_service_01");
        newService.setName("basic_service");
        newService.setPort(8081);

        NewService.Check serviceCheck = new NewService.Check();
        serviceCheck.setInterval("15s");

        newService.setCheck(serviceCheck);
        consul.agentServiceRegister(newService);
    }

    /**
     * 服务获取
     */
    public static void serviceGet() {
        // 获取所有服务
        Map<String, Service> map = consul.getAgentServices().getValue();
        map.values().forEach(System.out::println);
    }
}
