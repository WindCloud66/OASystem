package com.houyongju.cloud.dao;


import com.houyongju.cloud.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-01 17:27
 */
@Mapper
public interface UserMapper {
    // 定义添加操作[入参一个user对象]
    int insertUser(User user);
    // 删除用户
    int deleteUser(int id);
    // 更新用户
    int updateUser(User user);
    //定义查询所有user数据
    List<User> queryUser(HashMap map);
    //定义查询user总数据记录
    int queryUserCount(HashMap map);
    //--定义user的根据id查询的方法（入参id）
    User queryUserById(int id);
    //--定义user的登录查询方法（入参登录名和密码）
    User queryUserLogin(String loginName, String password);
}
