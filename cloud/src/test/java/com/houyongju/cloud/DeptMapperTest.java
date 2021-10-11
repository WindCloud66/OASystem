package com.houyongju.cloud;


import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.DeptMapper;
import com.houyongju.cloud.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


/**
 * @author HouYongJu
 * @create 2021-10-01 18:21
 */
@SpringBootTest
public class DeptMapperTest {
    @Autowired
    private DeptService deptService;


    @Test
    public void testSelectDept(){

//        Dept dept =new Dept();
//        dept.setId(12);
//        dept.setName("wind");
//        dept.setRemark("CCCC");
//
//        deptService.updateDept(dept);


//        Page page = new Page();
//        page.setCurrent(1);
//        page.setLimit(Integer.MAX_VALUE);
//        List<Dept> depts = deptService.queryDept(page, null);
//        System.out.println(depts);
//        int count = deptService.queryDeptCount(null);
//        System.out.println(count);
//        Dept dept = deptService.queryDeptById(2);
//        System.out.println(dept);
        deptService.deleteDept(12);
    }

//


}
