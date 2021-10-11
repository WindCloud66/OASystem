package com.oa.service.impl;

import com.oa.bean.Dept;
import com.oa.bean.PageInfo;
import com.oa.dao.DeptDao;
import com.oa.service.DeptService;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-09-29 20:32
 */
public class DeptServiceImpl implements DeptService {
    DeptDao deptDao = new DeptDao();
    @Override
    public List<Dept> queryDept() {
        return deptDao.queryDept();
    }

    @Override
    public List<Dept> queryDept(PageInfo page) {
        return deptDao.queryDept(page);
    }
}
