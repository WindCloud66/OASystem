package com.houyongju.demo;


import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@EnableDubboConfiguration
public class HouyongjuApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouyongjuApplication.class, args);
    }

}
