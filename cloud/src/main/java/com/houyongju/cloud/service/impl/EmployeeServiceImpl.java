package com.houyongju.cloud.service.impl;

import com.houyongju.cloud.bean.Employee;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.EmployeeMapper;
import com.houyongju.cloud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 19:20
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public List<Employee> queryEmployee(Page page, Employee Employee) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, page, Employee);
        return employeeMapper.queryEmployee(map);
    }

    @Override
    public int queryEmployeeCount(Employee Employee) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, null, Employee);
        return employeeMapper.queryEmployeeCount(map);
    }

    @Override
    public Employee queryEmployeeById(int employeeId) {
        return employeeMapper.queryEmployeeById(employeeId);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeMapper.deleteEmployee(id);
    }

    public void putBeanIntoMap(HashMap<String,Object> map,Page page,Employee employee ){
        if(page != null){
            map.put("limit",page.getLimit());
            map.put("offset",page.getOffset());
        }

        map.put("id",employee.getId());
        map.put("password",employee.getPassword());
        map.put("deptId",employee.getDeptId());
        map.put("jobId",employee.getJobId());
        map.put("name",employee.getName());
        map.put("cardId",employee.getCardId());
        map.put("phone",employee.getPhone());
        map.put("email",employee.getEmail());
        map.put("sex",employee.getSex());
        map.put("party",employee.getParty());
        map.put("race",employee.getRace());
        map.put("education",employee.getEducation());
        map.put("createDate",employee.getCreateDate());
        map.put("imgName",employee.getImgName());
    }
}
