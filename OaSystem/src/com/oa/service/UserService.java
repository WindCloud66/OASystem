package com.oa.service;

import com.oa.bean.PageInfo;
import com.oa.bean.User;

import javax.servlet.http.Part;
import java.util.List;

//管理员模块的业务层接口--定义规范方法（抽象方法）
public interface UserService {
    //定义添加操作[入参一个user对象][文件上传]
    public boolean insertUser(User user, Part part);
    //
    public boolean deleteUser(int id);
    //
    public boolean updateUser(User user,Part part);
    //
    public List<User> queryUser(PageInfo page);
    //
    public User queryUserById(int userid);
    //
    public User queryUserLogin(String name,String pass);
}
