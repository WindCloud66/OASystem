package com.houyongju.cloud.service;

import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.bean.User;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-07 22:39
 */
public interface UserService {
    //定义添加操作[入参一个user对象][文件上传]
    public int insertUser(User user);
    //
    public int deleteUser(int id);
    //
//    public int updateUser(User user, Part part);
    //
    public List<User> queryUser(Page page, User user);
    //
    public User queryUserById(int userid);
    //
    public User queryUserLogin(String name,String pass);
    //定义查询user总数据记录
    public int queryUserCount(User user);
}
