package com.houyongju.cloud.service.impl;

import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.bean.User;
import com.houyongju.cloud.dao.UserMapper;
import com.houyongju.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-07 22:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

//    @Override
//    public int updateUser(User user, Part part) {
//        return userMapper.updateUser(user);
//    }

    @Override
    public List<User> queryUser(Page page, User user) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("limit",page.getLimit());
        map.put("offset",page.getOffset());
        map.put("loginname",user.getLoginname());
        map.put("username", user.getUsername());
        map.put("status", user.getStatus());

        return userMapper.queryUser(map);
    }

    @Override
    public User queryUserById(int userid) {
        return userMapper.queryUserById(userid);
    }

    @Override
    public User queryUserLogin(String name, String pass) {
        return userMapper.queryUserLogin(name,pass);
    }

    @Override
    public int queryUserCount(User user) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("loginname",user.getLoginname());
        map.put("username", user.getUsername());
        map.put("status", user.getStatus());
        return userMapper.queryUserCount(map);
    }
}
