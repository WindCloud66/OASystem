package com.houyongju.cloud.dao;

import com.houyongju.cloud.bean.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 19:10
 */
@Mapper
public interface JobMapper {
    // 定义添加操作[入参一个Job对象]
    int insertJob(Job job);
    // 删除用户
    int deleteJob(int id);
    //     更新用户
    int updateJob(Job job);
    //定义查询所有Job数据
    List<Job> queryJob(HashMap map);
    //定义查询Job总数据记录
    int queryJobCount(HashMap map);
    //--定义Job的根据id查询的方法（入参id）
    Job queryJobById(int id);
    //--定义Job的登录查询方法（入参登录名和密码）
//    Job queryJobLogin(String loginName, String password);

}
