package com.houyongju.cloud.service.impl;

import com.houyongju.cloud.bean.Job;
import com.houyongju.cloud.bean.Job;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.JobMapper;
import com.houyongju.cloud.dao.JobMapper;
import com.houyongju.cloud.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 20:33
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;
    @Override
    public int insertJob(Job job) {
        return jobMapper.insertJob(job);
    }

    @Override
    public List<Job> queryJob(Page page, Job job) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, page, job);
        return jobMapper.queryJob(map);
    }

    @Override
    public int queryJobCount(Job job) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, null, job);
        return jobMapper.queryJobCount(map);
    }

    @Override
    public Job queryJobById(int jobId) {
        return jobMapper.queryJobById(jobId);
    }

    @Override
    public int updateJob(Job job) {
        return jobMapper.updateJob(job);
    }

    @Override
    public int deleteJob(int id) {
        return jobMapper.deleteJob(id);
    }
    public void putBeanIntoMap(HashMap<String,Object> map, Page page, Job job ){
        if(page != null){
            map.put("limit",page.getLimit());
            map.put("offset",page.getOffset());
        }
        if(job != null){

            map.put("name", job.getName());
            map.put("remark", job.getRemark());
        }


    }
}
