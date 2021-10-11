package com.houyongju.cloud.dao;

import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 19:02
 */
@Mapper
public interface DeptMapper {
    // 定义添加操作[入参一个Dept对象]
    int insertDept(Dept dept);
    // 删除用户
    int deleteDept(int id);
    //     更新用户
    int updateDept(Dept dept);
    //定义查询所有Dept数据
    List<Dept> queryDept(HashMap map);
    //定义查询Dept总数据记录
    int queryDeptCount(HashMap map);
    //--定义Dept的根据id查询的方法（入参id）
    Dept queryDeptById(int id);
}
