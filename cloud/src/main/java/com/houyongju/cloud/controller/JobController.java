package com.houyongju.cloud.controller;

import com.houyongju.cloud.bean.Job;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.bean.User;
import com.houyongju.cloud.service.JobService;

import com.houyongju.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HouYongJu
 * @create 2021-10-07 22:54
 */
@Controller

public class JobController {
    @Autowired
    private JobService jobService;

   


    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;
//(searchloginname=${searchloginname})(searchUserName=${searchUserName})(searchStatus=${searchStatus})
    @RequestMapping(path = "/job/list", method = RequestMethod.GET)
    public String getJobList( Model model, Page page,Job searchJob){

        if(searchJob == null){
            searchJob = new Job();
        }

        // 用户分页信息
        page.setLimit(5);
        page.setPath("/job/list/");
        page.setRows(jobService.queryJobCount(searchJob));

        // 用户列表
        Object selected = null;
        Object selectValue = null;
        List<Job> jobList = jobService.queryJob(page, searchJob);
        List<String> jobAttr = new ArrayList<>();
        Field[] fields = searchJob.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            String fieldName =  field.getName();
            if("id".equalsIgnoreCase(fieldName)){
                continue;
            }
            jobAttr.add(fieldName);
            try {
                Object value = field.get(searchJob);
                if(value != null){
                    selected = fieldName;
                    selectValue = value;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        model.addAttribute("jobList", jobList);
        model.addAttribute("jobAttr", jobAttr);
        model.addAttribute("selected", selected);
        model.addAttribute("selectValue", selectValue);
        model.addAttribute("searchJob", searchJob);


        return "/jobList";
    }
    @RequestMapping(path = "/job/insertJob", method = RequestMethod.GET)
    public String getAddJob( Model model){

        return "/jobInsert";
    }

    @RequestMapping(path = "/job/insertJob", method = RequestMethod.POST)
    public String addJob(Job job, Model model){

        jobService.insertJob(job);
        return "redirect:/job/list/";
    }
//
    @RequestMapping(path = "/job/updateJob/{jobId}", method = RequestMethod.GET)
    public String updateJob(@PathVariable(value = "jobId" ,required = true)int jobId, Model model){

        Job job = jobService.queryJobById(jobId);
        model.addAttribute("job", job);


        return "/jobUpdate";
    }
    @RequestMapping(path = "/job/updateJob", method = RequestMethod.POST)
    public String updateJob(Job job, Model model){
        if(job != null)
            jobService.updateJob(job);

        return "redirect:/job/list/";
    }
    @RequestMapping(path = "/job/deleteJob", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(value = "jobId",required = true) int jobId, Model model){
        jobService.deleteJob(jobId);

        // 用户分页信息
        int rows = jobService.queryJobCount(null);
        int limit = 5;
        int current = rows % limit == 0 ? rows / limit : rows / limit + 1;
        Page page = new Page();
        page.setLimit(5);
        page.setCurrent(current);
        page.setPath("/job/list/");
        page.setRows(rows);
        model.addAttribute("page",page);

        return "/JobList";

    }
}
