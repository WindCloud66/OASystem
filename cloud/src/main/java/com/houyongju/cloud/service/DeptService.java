package com.houyongju.cloud.service;

import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Page;

import java.util.List;


/**
 * @author HouYongJu
 * @create 2021-10-08 20:32
 */
public interface DeptService {

    // 定义添加操作[入参一个Dept对象]
    int insertDept(Dept dept);
    // 查询符合条件
    public List<Dept> queryDept(Page page, Dept dept);

    //定义查询Dept总数据记录
    public int queryDeptCount(Dept dept);
    // 通过id查询Dept
    public Dept queryDeptById(int deptId);
    // 更新
    int updateDept(Dept dept);
    //
    public int deleteDept(int id);
}
