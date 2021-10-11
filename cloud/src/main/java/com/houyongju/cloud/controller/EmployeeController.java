package com.houyongju.cloud.controller;

import com.houyongju.cloud.bean.*;
import com.houyongju.cloud.dao.EmployeeMapper;
import com.houyongju.cloud.service.DeptService;
import com.houyongju.cloud.service.EmployeeService;
import com.houyongju.cloud.service.JobService;
import com.houyongju.cloud.service.UserService;
import com.houyongju.cloud.util.OaSystemUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author HouYongJu
 * @create 2021-10-07 22:54
 */
@Controller

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobService jobService;

    @Autowired
    private DeptService deptService;
    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;
//(searchloginname=${searchloginname})(searchUserName=${searchUserName})(searchStatus=${searchStatus})
    @RequestMapping(path = "/employee/list", method = RequestMethod.GET)
    public String getEmployeeList(Employee searchEmployee, Model model, Page page){

        if(searchEmployee == null){
            searchEmployee = new Employee();
        }



        // 用户分页信息
        page.setLimit(5);
        page.setPath("/employee/list/");
        page.setRows(employeeService.queryEmployeeCount(searchEmployee));
        // 用户列表
        List<Employee> list = employeeService.queryEmployee(page, searchEmployee);
        List<Map<String,Object>> employeeVoList = new ArrayList<>();
        if(list != null){
            for(Employee employee: list){
                Map<String,Object> map = new HashMap<>();
                map.put("employee", employee);
                long deptId = employee.getDeptId();
                long jobId = employee.getJobId();
                String deptName = deptService.queryDeptById(employee.getDeptId()).getName();
                String jobName = jobService.queryJobById(employee.getJobId()).getName();
                map.put("deptName", deptName);
                map.put("jobName", jobName);
                employeeVoList.add(map);
            }
        }
        Employee employee = new Employee();
        List<String> employeeAttr = new ArrayList<>();

        Field[] fields = employee.getClass().getDeclaredFields();
        for(Field field : fields){
            String fieldName =  field.getName();

            employeeAttr.add(fieldName);
        }




        model.addAttribute("employeeAttr", employeeAttr);

        model.addAttribute("employeeVoList", employeeVoList);

        return "/employeeList";
    }
    @RequestMapping(path = "/employee/insertEmployee", method = RequestMethod.GET)
    public String getAddEmployee( Model model){
        // page 只用于查询数据库
        Page page = new Page();
        page.setCurrent(1);
        page.setLimit(Integer.MAX_VALUE);
        List<Dept> deptList = deptService.queryDept(page, null);
        List<Job> jobList = jobService.queryJob(page, null);

        model.addAttribute("deptList", deptList);
        model.addAttribute("jobList", jobList);

        return "/employeeInsert";
    }

    @RequestMapping(path = "/employee/insertEmployee", method = RequestMethod.POST)
    public String addEmployee(Employee employee, Model model){
        employeeService.insertEmployee(employee);
        return "redirect:/employee/list/";
    }
    @RequestMapping(path = "/employee/updateEmployee/{employeeId}", method = RequestMethod.GET)
    public String updateEmployee(@PathVariable(value = "employeeId" ,required = true)int employeeId, Model model){
        // page 只用于查询数据库
        Page page = new Page();
        page.setCurrent(1);
        page.setLimit(Integer.MAX_VALUE);

        List<Dept> deptList = deptService.queryDept(page, null);
        List<Job> jobList = jobService.queryJob(page, null);
        Employee employee = employeeService.queryEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("deptList", deptList);
        model.addAttribute("jobList", jobList);

        return "/employeeUpdate";
    }
    @RequestMapping(path = "/employee/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(Employee employee, Model model){
        if(employee != null)
            employeeService.updateEmployee(employee);

        return "redirect:/employee/list/";
    }
    @RequestMapping(path = "/employee/deleteEmployee", method = RequestMethod.POST)
    public String deleteUser(int employeeId, Model model){
        employeeService.deleteEmployee(employeeId);
        Employee employee = new Employee();
        // 用户分页信息
        int rows = employeeService.queryEmployeeCount(employee);
        int limit = 5;
        int current = rows % limit == 0 ? rows / limit : rows / limit + 1;
        Page page = new Page();
        page.setLimit(5);
        page.setCurrent(current);
        page.setPath("/user/list/");
        page.setRows(rows);
        model.addAttribute("msg",true);
        model.addAttribute("page",page);

        return "/employeeList";

    }
}
