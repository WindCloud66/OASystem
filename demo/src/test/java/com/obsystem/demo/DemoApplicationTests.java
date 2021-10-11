package com.obsystem.demo;

import com.obsystem.demo.bean.User;
import com.obsystem.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
class DemoApplicationTests {
//    @Autowired
//    private UserMapper userMapper;
    @Test
    void contextLoads() {
        // 查询所有数据
//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);

    }



}
