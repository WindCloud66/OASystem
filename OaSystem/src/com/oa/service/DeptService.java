package com.oa.service;

import com.oa.bean.Dept;
import com.oa.bean.PageInfo;
import com.oa.bean.User;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-09-29 20:32
 */
public interface DeptService {
    //
    public List<Dept> queryDept();

    public List<Dept> queryDept(PageInfo page);
}
