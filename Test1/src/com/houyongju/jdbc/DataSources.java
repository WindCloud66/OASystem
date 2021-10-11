package com.houyongju.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author HouYongJu
 * @create 2021-09-26 18:01
 */
public class DataSources {
    static String url = "jdbc:mysql://localhost:3306/ssm_crud?serverTimezone=GMT%2B8";
    static String name = "root";
    static String password = "123456";
    //2、加载MySQL的驱动类
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //3、定义一个静态方法来返回数据库的链接对象
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,name,password);
    }

    //4、定义一个静态方法来关闭数据库链接
    public static void close(Connection connection) throws SQLException {
        //判断对象是否为空
        if(connection!=null){
            connection.close();
        }
    }
}
