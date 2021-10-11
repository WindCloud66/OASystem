package com.oa.controller;


import com.oa.bean.PageInfo;
import com.oa.bean.User;
import com.oa.dao.UserDao;
import com.oa.service.UserService;
import com.oa.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

//使用UserController来接收和处理user模块的所有请求操作
//目的：一个请求处理类映射多个uri
@WebServlet(urlPatterns = {"/loginUser", "/deleteUser", "/queryUser", "/insertUser", "/toUpdateUser", "/updateUser"})
@MultipartConfig //支持文件上传的注解
public class UserController extends HttpServlet {

    //获取业务层接口的对象
    UserService userService = new UserServiceImpl();

    //doGet方法是用于接收get请求的
    @Override   //          request  请求对象           response  响应对象
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//请求对象设置编码
        //获取请求uri
        String uri = request.getRequestURI();
        // response.getWriter().append("UserController--"+uri);
        if (uri.contains("loginUser")) {
            loginUser(request, response);    //登录请求处理方法
        } else if (uri.contains("queryUser")) {
            queryUser(request, response);     //查询请求处理方法
        } else if (uri.contains("insertUser")) {
            insertUser(request, response);   //添加请求处理方法
        } else if (uri.contains("toUpdateUser")) {
            toUpdateUser(request, response); //修改页面请求
        } else if (uri.contains("updateUser")) {
            updateUser(request, response); //修改请求
        } else if (uri.contains("deleteUser")) {
            deleteUser(request, response); //删除请求
        }
    }

    //删除请求处理方法
    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从请求对象中获取请求参数 通过getParameter方法根据key来获取value
        String str_id = request.getParameter("id");
        int id = Integer.parseInt(str_id);//转换为int类型
        //2、调用业务层方法实现功能
        userService.deleteUser(id);
        //3、作出响应--重新查询
        request.getRequestDispatcher("queryUser").forward(request, response);
    }

    //修改请求处理方法
    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从请求对象中获取请求参数 通过getParameter方法根据key来获取value
        String str_id = request.getParameter("id");
        int id = Integer.parseInt(str_id);//转换为int类型
        String loginname = request.getParameter("loginname");//没有获取到该数据则为null
        String password = request.getParameter("password");
        String str_status = request.getParameter("status");
        int status = Integer.parseInt(str_status);//转换为int类型
        String username = request.getParameter("username");
        //创建user对象将数据进行封装
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setLoginname(loginname);
        user.setStatus(status);
        //获取文件区域的对象数据
        Part filepart = request.getPart("filepart");
        //2、调用业务层方法实现功能
        boolean isok = userService.updateUser(user, filepart);
        //3、作出响应--跳转页面
        if (isok) {//成功则重新查询数据
            request.getRequestDispatcher("queryUser").forward(request, response);
        } else {//失败跳转回到修改页面
            request.getRequestDispatcher("toUpdateUser?id=" + id).forward(request, response);
        }
    }

    //跳转查询请求处理方法
    protected void toUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取id，根据id查询user
        String userid = request.getParameter("id");
        int id = Integer.parseInt(userid);
        User user = userService.queryUserById(id);
        //2、保存user对象数据保存在属性作用
        request.setAttribute("user", user);
        //3、跳转UserUpdate.jsp页面
        request.getRequestDispatcher("UserUpdate.jsp").forward(request, response);
    }

    //查询请求处理方法
    protected void queryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取跳转页面
        String pn = request.getParameter("pn");
        int pageNo = 1;
        if(pn!=null){
            pageNo = Integer.parseInt(pn);
        }
        //创建分页的对象
        PageInfo page = new PageInfo(pageNo,UserDao.queryUserCount());
        //1、调用业务层方法查询数据
        List<User> userList = userService.queryUser(page);
        //2、保存数据到属性作用域中
        request.setAttribute("userList", userList);
        request.setAttribute("pageInfo", page);
        //3、跳转到UserIndex.jsp页面进行数据显示
        request.getRequestDispatcher("deptIndex.jsp").forward(request, response);
    }

    //添加请求处理方法
    protected void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从请求对象中获取请求参数 通过getParameter方法根据key来获取value
        String loginname = request.getParameter("loginname");//没有获取到该数据则为null
        String password = request.getParameter("password");
        String str_status = request.getParameter("status");
        int status = Integer.parseInt(str_status);//转换为int类型
        String username = request.getParameter("username");
        //创建user对象将数据进行封装
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLoginname(loginname);
        user.setStatus(status);
        //获取文件区域的对象数据
        Part filepart = request.getPart("filepart");
        //2、调用业务层方法实现功能
        boolean isok = userService.insertUser(user, filepart);
        //3、作出响应--跳转页面
        if (isok) {//成功则跳转到首页
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {//失败跳转回到登录页面
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    //登录请求处理方法
    protected void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从请求对象中获取请求参数【登录名、密码】 通过getParameter方法根据key来获取value
        String loginname = request.getParameter("loginname");//没有获取到该数据则为null
        String password = request.getParameter("password");
        //2、进行登录校验\做一个非空判断
        if (loginname == null || loginname.equals("")) {
            //在Servlet请求处理类中保存信息（key-value）到属性作用域(容器)【保存】 request作用域
            request.setAttribute("message", "登录名不能为空！");
            //3、进行响应--登录名或密码不能为空
            request.getRequestDispatcher("login.jsp").forward(request, response);
            //结束方法
            return;
        }
        if (password == null || password.equals("")) {
            //【保存】 request作用域
            request.setAttribute("message", "密码不能为空！");
            //3、进行响应--登录名或密码不能为空
            request.getRequestDispatcher("login.jsp").forward(request, response);
            //结束方法
            return;
        }
        //controller调用业务层的方法进行登录查询
        User loginUser = userService.queryUserLogin(loginname, password);
        //校验
        if (loginUser != null) {//表示登录成功--有该用户数据
            //3、进行响应--成功--跳转到首页
            request.getSession().setAttribute("NowUser", loginUser);
            request.getRequestDispatcher("index.jsp").forward(request, response);//转发：无法访问外部程序
        } else {
            //3、进行响应--失败--跳转到登录页面
            //【保存】 request作用域
            request.setAttribute("message", "用户名或密码错误！");
            request.setAttribute("loginname", loginname);
            request.setAttribute("password", password);
            //转发是请求链没有断开的
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    //doPost方法是用于接收Post请求的
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用了上面的doGet，目的是方便操作【不管是get、post都可以在doGet中编写处理代码】
        doGet(request, response);
    }
}
