package com.oa.dao;

import com.oa.bean.PageInfo;
import com.oa.bean.User;
import com.oa.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//dao是表示为数据库持久层的操作类  UserDao是针对user表的数据库操作
public class UserDao {
    //定义添加操作[入参一个user对象]
    public boolean insertUser(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "insert into user_inf(loginname,password,status,username,imgname) values(?,?,?,?,?);";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //给占位符进行赋值 //第一个参数为占位符的位置
            ps.setString(1, user.getLoginname());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getStatus());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getImgname());
            //4、通过sql语句对象来执行sql语句
            int result = ps.executeUpdate();//添加、删除、修改sql都可以使用executeUpdate方法执行
            // result为执行sql语句的影响行数、成功为大于0，否则小于等于0
            return result > 0;
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
            ps.setInt(1, id);
            //4、通过sql语句对象来执行sql语句
            int result = ps.executeUpdate();//添加、删除、修改sql都可以使用executeUpdate方法执行
            // result为执行sql语句的影响行数、成功为大于0，否则小于等于0
            return result > 0;
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

    //定义修改
    public boolean updateUser(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "update user_inf set loginname=?,password=?,status=?,username=?,imgname=? where id=?;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //给占位符进行赋值 //第一个参数为占位符的位置
            ps.setString(1, user.getLoginname());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getStatus());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getImgname());
            ps.setInt(6, user.getId());
            //4、通过sql语句对象来执行sql语句
            int result = ps.executeUpdate();//添加、删除、修改sql都可以使用executeUpdate方法执行
            // result为执行sql语句的影响行数、成功为大于0，否则小于等于0
            return result > 0;
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
            while (result.next()) {//next方法用于判断当前的结果集中是否有下一条数据
                //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
                int id = result.getInt("id");
                String loginname = result.getString("loginname");
                String pass = result.getString("password");
                int status = result.getInt("status");
                String date = result.getString("createdate");
                String username = result.getString("username");
                String imgname = result.getString("imgname");
                //将数据进行封装，封装到user对象中
                User user = new User(id, loginname, pass, status, date, username, imgname);
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

    //定义根据分页信息查询user数据
    public List<User> queryUser(PageInfo page) {
        //创建一个list集合用于保存查询到数据
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select * from user_inf limit ?,?";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            // 设置limit参数数据
            ps.setInt(1, page.getStartIndex());
            ps.setInt(2, page.getPageSize());
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
            // result为结果集，里面包含了所有的查询结果
            while (result.next()) {//next方法用于判断当前的结果集中是否有下一条数据
                //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
                int id = result.getInt("id");
                String loginname = result.getString("loginname");
                String pass = result.getString("password");
                int status = result.getInt("status");
                String date = result.getString("createdate");
                String username = result.getString("username");
                String imgname = result.getString("imgname");
                //将数据进行封装，封装到user对象中
                User user = new User(id, loginname, pass, status, date, username, imgname);
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

    //定义查询user总数据记录
    public static int queryUserCount() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select count(*) from user_inf";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
            // result为结果集，里面包含了所有的查询结果
            if (result.next()) {//next方法用于判断当前的结果集中是否有下一条数据
                return result.getInt(1);
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
        //没有返回0
        return 0;
    }

    //--定义user的根据id查询的方法（入参id）
    public User queryUserById(int userid) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select * from user_inf where id=?;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userid);
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
            // result为结果集，里面包含了所有的查询结果
            while (result.next()) {//next方法用于判断当前的结果集中是否有下一条数据
                //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
                int id = result.getInt("id");
                String loginname = result.getString("loginname");
                String pass = result.getString("password");
                int status = result.getInt("status");
                String date = result.getString("createdate");
                String username = result.getString("username");
                String imgname = result.getString("imgname");
                //将数据进行封装，封装到user对象中
                User user = new User(id, loginname, pass, status, date, username, imgname);
                //将user进行返回
                return user;
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
        return null;
    }

    //--定义user的登录查询方法（入参登录名和密码）
    public User queryUserLogin(String name, String pass) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select * from user_inf where loginname=? and password=?;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pass);
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
            // result为结果集，里面包含了所有的查询结果
            if (result.next()) {//next方法用于判断当前的结果集中是否有下一条数据
                //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
                int id = result.getInt("id");
                String loginname = result.getString("loginname");
                String password = result.getString("password");
                int status = result.getInt("status");
                String date = result.getString("createdate");
                String username = result.getString("username");
                String imgname = result.getString("imgname");
                //将数据进行封装，封装到user对象中
                User user = new User(id, loginname, password, status, date, username, imgname);
                //将user进行返回
                return user;
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
        //返回null
        return null;
    }
}
