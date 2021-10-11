package com.oa.dao;

import com.oa.bean.Dept;
import com.oa.bean.PageInfo;
import com.oa.bean.User;
import com.oa.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-09-29 20:28
 */
public class DeptDao {
    //定义查询所有Dept数据
    public List<Dept> queryDept() {
        //创建一个list集合用于保存查询到数据
        List<Dept> deptList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select * from dept_inf;";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
            // result为结果集，里面包含了所有的查询结果
            while(result.next()){//next方法用于判断当前的结果集中是否有下一条数据
                //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
                int id = result.getInt("id");
                String name = result.getString("name");
                String remark = result.getString("remark");

                //将数据进行封装，封装到dept_inf对象中
                Dept dept = new Dept(id, name, remark);

                //将dept对象添加到集合中
                deptList.add(dept);
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
        return deptList;
    }

    //定义根据分页信息查询user数据
    public List<Dept> queryDept(PageInfo page) {
        //创建一个list集合用于保存查询到数据
        List<Dept> deptList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select * from Dept_inf limit ?,?";
            //3、通过数据库链接来获取sql的语句对象【语句对象是用于执行sql语句的】
            // prepareStatement方法来得到一个预编译的sql语句对象，要入参sql语句
            ps = connection.prepareStatement(sql);
            // 设置limit参数数据
            ps.setInt(1, page.getStartIndex());
            ps.setInt(2, page.getPageSize());
            //4、通过sql语句对象来执行sql语句
            ResultSet result = ps.executeQuery();//查询语句使用executeQuery方法执行，返回的是结果集ResultSet
            // result为结果集，里面包含了所有的查询结果
            while(result.next()){//next方法用于判断当前的结果集中是否有下一条数据
                //有下一条数据，则通过结果集中的get()方法来获取数据   注意这是一条记录
                int id = result.getInt("id");
                String name = result.getString("name");
                String remark = result.getString("remark");

                //将数据进行封装，封装到dept_inf对象中
                Dept dept = new Dept(id, name, remark);

                //将dept对象添加到集合中
                deptList.add(dept);
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
        return deptList;
    }

    //定义查询user总数据记录
    public static int queryDeptCount() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接的对象
            connection = DataSources.getConnection();
            //2、定义sql语句
            String sql = "select count(*) from dept_inf";
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
}
