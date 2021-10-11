package com.houyongju.cloud;


import com.houyongju.cloud.bean.User;
import com.houyongju.cloud.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author HouYongJu
 * @create 2021-10-01 18:21
 */
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectUser(){
//        User user = new User();
//        user.setId(18);
//        user.setImgname("null");
//        user.setLoginname("99");
//        user.setPassword("6666");
//        user.setStatus(0);
//        user.setUsername("XX");
//        userMapper.insertUser(user);
//        List<User> users = userMapper.queryUser(0, 5);
//        for(User user:users){
//            System.out.println(user);
//        }
//        User user = userMapper.queryUserById(18);
//        System.out.println(user);
//        int i = userMapper.queryUserCount();
//        System.out.println(i);
//        User user1 = userMapper.queryUserLogin("99", "6666");
//        System.out.println(user1);
            userMapper.deleteUser(18);

    }



}
