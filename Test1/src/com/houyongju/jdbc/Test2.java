package com.houyongju.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author HouYongJu
 * @create 2021-09-26 18:04
 */
public class Test2 {
    public static void main(String[] args) throws SQLException {
        // 数据库连接
        Connection connection = DataSources.getConnection();

        System.out.println("获取到数据库链接："+connection);
        //实现添加数据的操作
        //4、定义sql语句
        String sql = "select * from user_inf;";
        //5、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
        // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
        PreparedStatement ps = connection.prepareStatement(sql);
        //6、通过sql语句对象来执行sql语句
        ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
        // result为结果集，里面包含了所有的查询结果
        while(result.next()){//next方法用于判断当前的结果集中是否有下一条数据
            //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
            int id = result.getInt("id");
            String loginname = result.getString("loginname");
            String pass = result.getString("password");
            int status = result.getInt("status");
            Date date = result.getDate("createdate");
            String username = result.getString("username");
            String imgname = result.getString("imgname");
            //输出
            System.out.println("id="+id+";loginname="+loginname+";pass="+pass+";status="
                    +status+";date="+date+";username="+username+";imgname="+imgname);
        }
        //7、释放资源
        if(ps !=null)
            ps.close();
        if(connection != null)
            connection.close();
    }
}
