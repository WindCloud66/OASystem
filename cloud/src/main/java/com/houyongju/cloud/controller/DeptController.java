package com.houyongju.cloud.controller;

import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.service.DeptService;
import com.houyongju.cloud.service.DeptService;
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
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-11 15:19
 */
@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;




    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;
    //(searchloginname=${searchloginname})(searchUserName=${searchUserName})(searchStatus=${searchStatus})
    @RequestMapping(path = "/dept/list", method = RequestMethod.GET)
    public String getDeptList( Model model, Page page, Dept searchDept){
        if(searchDept == null){
            searchDept = new Dept();
        }
        // 用户分页信息
        page.setLimit(5);
        page.setPath("/dept/list");
        page.setRows(deptService.queryDeptCount(searchDept));
        // 用户列表
        Object selected = null;
        Object selectValue = null;
        List<Dept> deptList = deptService.queryDept(page, searchDept);
        List<String> deptAttr = new ArrayList<>();
        Field[] fields = searchDept.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            String fieldName =  field.getName();
            if("id".equalsIgnoreCase(fieldName)){
                continue;
            }
            deptAttr.add(fieldName);
            try {
                Object value = field.get(searchDept);
                if(value != null){
                    selected = fieldName;
                    selectValue = value;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("deptList", deptList);
        model.addAttribute("deptAttr", deptAttr);
        model.addAttribute("selected", selected);
        model.addAttribute("selectValue", selectValue);

        return "/deptList";
    }
    @RequestMapping(path = "/dept/insertDept", method = RequestMethod.GET)
    public String getAddDept( Model model){

        return "/deptInsert";
    }
//
    @RequestMapping(path = "/dept/insertDept", method = RequestMethod.POST)
    public String addDept(Dept dept, Model model){

        deptService.insertDept(dept);
        return "redirect:/dept/list/";
    }

    @RequestMapping(path = "/dept/updateDept/{deptId}", method = RequestMethod.GET)
    public String updateDept(@PathVariable(value = "deptId" ,required = true)int deptId, Model model){

        Dept dept = deptService.queryDeptById(deptId);
        model.addAttribute("dept", dept);


        return "/deptUpdate";
    }
    @RequestMapping(path = "/dept/updateDept", method = RequestMethod.POST)
    public String updateDept(Dept dept, Model model){
        if(dept != null)
            deptService.updateDept(dept);

        return "redirect:/dept/list/";
    }
    @RequestMapping(path = "/dept/deleteDept", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(value = "deptId",required = true) int deptId, Model model){
        deptService.deleteDept(deptId);

        // 用户分页信息
        int rows = deptService.queryDeptCount(null);
        int limit = 5;
        int current = rows % limit == 0 ? rows / limit : rows / limit + 1;
        Page page = new Page();
        page.setLimit(5);
        page.setCurrent(current);
        page.setPath("/dept/list/");
        page.setRows(rows);
        model.addAttribute("page",page);

        return "/deptList";

    }
}
