package com.houyongju.demo.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import top.snailclimb.service.HelloService;
import org.springframework.stereotype.Component;



@Component
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
