package com.houyongju.jdbc;

import com.mysql.cj.util.StringUtils;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-09-26 18:11
 */
public class UserDao {
    // 根据用户id 返回用户信息
    public boolean login(User user){
        if(user == null || user.getId() == 0 || StringUtils.isNullOrEmpty(user.getLoginname()) || StringUtils.isNullOrEmpty(user.getPassword())){
            return false;
        }
        String loginName = user.getLoginname();
        String userPassword = user.getPassword();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "SELECT * FROM ssm_crud.user_inf where ID = ?;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //给占位符进行赋值 //第一个参数为占位符的位置
            ps.setInt(1,user.getId());
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//添加、删除、修改sql都可以使用executeUpdate方法执行
            result.next();
            String pass = result.getString("password");
            String loginname = result.getString("loginname");


            if(loginName.equals(loginname) && userPassword.equals(pass)){
                return true;
            }


            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、释放资源
            try {
                if(ps != null)
                    ps.close();
                DataSources.close(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    // 根据用户id 返回用户信息
    public User insertUserForId(int id){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "SELECT * FROM ssm_crud.user_inf where ID = ?;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //给占位符进行赋值 //第一个参数为占位符的位置
            ps.setInt(1,id);
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//添加、删除、修改sql都可以使用executeUpdate方法执行
// result为执行sql语句的影响行数、成功为大于0，否则小于等于0

            result.next();
            String loginname = result.getString("loginname");
            String pass = result.getString("password");
            int status = result.getInt("status");
            String date = result.getString("createdate");
            String username = result.getString("username");
            String imgname = result.getString("imgname");
            //将数据进行封装，封装到user对象中
            User user = new User(id,loginname,pass,status,date,username,imgname);

            return user;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、释放资源
            try {
                if(ps != null)
                    ps.close();
                DataSources.close(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    //1、将操作以类和方法的形式进行封装 2、通过方法入参实现数据的动态入参【目的：实现代码复用】
    //  -- 字段比较多，进行sql语句拼接，会比较困难而且容易出错【容错性】
    //  -- 容易受到非法sql的入侵【不安全】
    //  解决方案：使用预编译的sql语句对象结合占位符?
    //定义添加操作[入参一个user对象]
    public boolean insertUser(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "insert into user_inf(loginname,password,status,username) values(?,?,?,?);";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //给占位符进行赋值 //第一个参数为占位符的位置
            ps.setString(1,user.getLoginname());
            ps.setString(2,user.getPassword());
            ps.setInt(3,user.getStatus());
            ps.setString(4,user.getUsername());
            //4、通过sql语句对象来执行sql语句
            int result = ps.executeUpdate();//添加、删除、修改sql都可以使用executeUpdate方法执行
            // result为执行sql语句的影响行数、成功为大于0，否则小于等于0
            return result>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、释放资源
            try {
                if(ps != null)
                    ps.close();
                DataSources.close(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    //定义删除
    public boolean deleteUser(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "delete from user_inf where id=?;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //给占位符赋值
            ps.setInt(1,id);
            //4、通过sql语句对象来执行sql语句
            int result = ps.executeUpdate();//添加、删除、修改sql都可以使用executeUpdate方法执行
            // result为执行sql语句的影响行数、成功为大于0，否则小于等于0
            return result>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、释放资源
            try {
                if(ps != null)
                    ps.close();
                DataSources.close(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    //定义修改
    public boolean updateUser(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "update user_inf set loginname=?,password=?,status=?,username=? where id=?;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //给占位符进行赋值 //第一个参数为占位符的位置
            ps.setString(1,user.getLoginname());
            ps.setString(2,user.getPassword());
            ps.setInt(3,user.getStatus());
            ps.setString(4,user.getUsername());
            ps.setInt(5,user.getId());
            //4、通过sql语句对象来执行sql语句
            int result = ps.executeUpdate();//添加、删除、修改sql都可以使用executeUpdate方法执行
            // result为执行sql语句的影响行数、成功为大于0，否则小于等于0
            return result>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、释放资源
            try {
                ps.close();
                DataSources.close(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    //定义查询所有user数据
    public List<User> queryUser() {
        //创建一个list集合用于保存查询到数据
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select * from user_inf;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
            // result为结果集，里面包含了所有的查询结果
            while(result.next()){//next方法用于判断当前的结果集中是否有下一条数据
                //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
                int id = result.getInt("id");
                String loginname = result.getString("loginname");
                String pass = result.getString("password");
                int status = result.getInt("status");
                String date = result.getString("createdate");
                String username = result.getString("username");
                String imgname = result.getString("imgname");
                //将数据进行封装，封装到user对象中
                User user = new User(id,loginname,pass,status,date,username,imgname);
                //将user对象添加到集合中
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、释放资源
            try {
                ps.close();
                DataSources.close(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //返回集合对象
        return userList;
    }
}
