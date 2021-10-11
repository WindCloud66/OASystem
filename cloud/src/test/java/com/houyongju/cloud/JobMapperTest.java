package com.houyongju.cloud;


import com.houyongju.cloud.bean.Job;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.JobMapper;
import com.houyongju.cloud.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author HouYongJu
 * @create 2021-10-01 18:21
 */
@SpringBootTest
public class JobMapperTest {
    @Autowired
    private JobService jobService;


    @Test
    public void testSelectJob(){
        // 查询符合条件
//        Job job =new Job();
//        job.setId(10);
//        job.setName("wind");
//        job.setRemark("C");
//        jobService.updateJob(job);
//        int updateJob(Job job);
//        public int deleteJob(int id);
//        System.out.println(Job1);
//        Page page = new Page();
//        page.setCurrent(1);
//        page.setLimit(Integer.MAX_VALUE);
//        List<Job> jobs = jobService.queryJob(page, null);
//        System.out.println(jobs);
//        int count = jobService.queryJobCount(null);
//        System.out.println(count);
//        Job job = jobService.queryJobById(2);
//        System.out.println(job);
        jobService.deleteJob(10);
    }



}
