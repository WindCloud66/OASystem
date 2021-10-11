package com.houyongju.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author HouYongJu
 * @create 2021-09-26 11:29
 */
public class jdbcTest1 {
    public static void main(String[] args) throws Exception {
        //1、提供数据库链接信息
        //             jdbc:mysql://ip地址:端口号/数据库名?访问参数
        String url = "jdbc:mysql://localhost:3306/ssm_crud?serverTimezone=GMT%2B8";
        String name = "root";
        String password = "123456";
        //2、加载MySQL的驱动类
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3、获取数据库链接的对象
        Connection connection = DriverManager.getConnection(url,name,password);
        //
        System.out.println("获取到数据库链接："+connection);
        //实现添加数据的操作
        //4、定义sql语句
        String sql = "insert into user_inf(loginname,password,status,username) values('gec','123',0,'gec_admin');";
        //5、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
        // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
        PreparedStatement ps = connection.prepareStatement(sql);
        //6、通过sql语句对象来执行sql语句
        int result = ps.executeUpdate();//添加、删除、修改sql都可以使用executeUpdate方法执行
        // result为执行sql语句的影响行数、成功为大于0，否则小于等于0
        System.out.println("返回值为："+result);
        //7、释放资源
        ps.close();
        connection.close();
    }
}
