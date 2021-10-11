package com.houyongju.cloud.service;

import com.houyongju.cloud.bean.Job;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.bean.Employee;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 19:14
 */
public interface EmployeeService {

    // 定义添加操作[入参一个Employee对象]
    int insertEmployee(Employee employee);
    // 查询符合条件
    public List<Employee> queryEmployee(Page page, Employee Employee);

    //定义查询Employee总数据记录
    public int queryEmployeeCount(Employee Employee);
    // 通过id查询员工
    public Employee queryEmployeeById(int employeeId);
    // 更新
    int updateEmployee(Employee employee);
    //
    public int deleteEmployee(int id);
}
