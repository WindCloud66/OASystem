package com.houyongju.cloud.dao;

import com.houyongju.cloud.bean.Employee;
import com.houyongju.cloud.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 17:12
 */
@Mapper
public interface EmployeeMapper {
    // 定义添加操作[入参一个Employee对象]
    int insertEmployee(Employee employee);
    // 删除用户
    int deleteEmployee(int id);
//     更新用户
    int updateEmployee(Employee employee);
    //定义查询所有Employee数据
    List<Employee> queryEmployee(HashMap map);
    //定义查询Employee总数据记录
    int queryEmployeeCount(HashMap map);
    //--定义Employee的根据id查询的方法（入参id）
    Employee queryEmployeeById(int id);
    //--定义Employee的登录查询方法（入参登录名和密码）
//    Employee queryEmployeeLogin(String loginName, String password);
}