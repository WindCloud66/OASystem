package com.houyongju.cloud.service;

import com.houyongju.cloud.bean.Job;
import com.houyongju.cloud.bean.Page;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 19:14
 */
public interface JobService {

    // 定义添加操作[入参一个Job对象]
    int insertJob(Job job);
    // 查询符合条件
    public List<Job> queryJob(Page page, Job job);

    //定义查询Job总数据记录
    public int queryJobCount(Job job);
    // 通过id查询Job
    public Job queryJobById(int jobId);
    // 更新
    int updateJob(Job job);
    //
    public int deleteJob(int id);
}
