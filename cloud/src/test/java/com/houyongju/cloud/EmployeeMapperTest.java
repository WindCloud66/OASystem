package com.houyongju.cloud;

import com.houyongju.cloud.bean.Employee;
import com.houyongju.cloud.bean.User;
import com.houyongju.cloud.dao.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 17:28
 */
@SpringBootTest
public class EmployeeMapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    public void testSelectEmployee(){
//        List<Employee> employeeList = employeeMapper.queryEmployee(null);
//        for(Employee employee: employeeList){
//            System.out.println(employee);
//        }
//        int count = employeeMapper.queryEmployeeCount(null);
//        System.out.println(count);
        Employee employee = new Employee();
        employee.setId(4l);
        employee.setPassword("123456");
        employee.setDeptId(1);
        employee.setJobId(1);
        employee.setName("wind");
        employee.setCardId("4328011988");
        employee.setPhone("12345678901");
        employee.setEmail("123456@qq.com");
        employee.setSex(1);
        employee.setParty("党员");
        employee.setRace("汉");
        employee.setEducation("本科");
        employee.setImgName("wind");
        for(int i = 0; i < 20; i++){
            employeeMapper.insertEmployee(employee);
        }

    }
}
