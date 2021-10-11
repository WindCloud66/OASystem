package com.oa.util;

import com.oa.bean.Dept;
import com.oa.bean.PageInfo;
import com.oa.bean.User;
import com.oa.dao.DeptDao;
import com.oa.dao.UserDao;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        //创建dao对象
        DeptDao dao = new DeptDao();
        //创建分页对象
        PageInfo pageInfo = new PageInfo(1,11);
        //进行分页查询
        List<Dept> deptList = dao.queryDept(pageInfo);
        //
        for (Dept dept:deptList) {
            System.out.println(dept);
        }
    }
}
