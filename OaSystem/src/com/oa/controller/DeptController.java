package com.oa.controller;

import com.oa.bean.Dept;
import com.oa.bean.PageInfo;
import com.oa.bean.User;
import com.oa.dao.DeptDao;
import com.oa.dao.UserDao;
import com.oa.service.DeptService;
import com.oa.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-09-29 20:34
 */
@WebServlet(urlPatterns = {"/queryDept"})
public class DeptController extends HttpServlet {
    DeptService deptService = new DeptServiceImpl();
    //doGet方法是用于接收get请求的
    @Override   //          request  请求对象           response  响应对象
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//请求对象设置编码
        //获取请求uri
        String uri = request.getRequestURI();
        // response.getWriter().append("UserController--"+uri);
        if(uri.contains("queryDept")){
            queryDept(request,response);     //查询请求处理方法
        }
    }
    //查询请求处理方法
    protected void queryDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取跳转页面
        String pn = request.getParameter("pn");
        int pageNo = 1;
        if(pn!=null){
            pageNo = Integer.parseInt(pn);
        }
        //创建分页的对象
        PageInfo page = new PageInfo(pageNo, DeptDao.queryDeptCount());
        //1、调用业务层方法查询数据
        List<Dept> deptList = deptService.queryDept(page);
        //2、保存数据到属性作用域中
        request.setAttribute("deptList", deptList);
        request.setAttribute("pageInfo", page);
        //3、跳转到UserIndex.jsp页面进行数据显示
        request.getRequestDispatcher("DeptIndex.jsp").forward(request, response);
    }
}
