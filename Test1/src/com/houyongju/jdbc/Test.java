package com.houyongju.jdbc;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-09-26 18:11
 */
public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setLoginname("wind");
        user.setPassword("123456");
        user.setId(16);

        UserDao userDao = new UserDao();
        //查询测试
//        List<User> userList = userDao.queryUser();
//        for (User user:userList){
//            System.out.println(user);
//        }

        System.out.println(userDao.login(user));
    }
}
