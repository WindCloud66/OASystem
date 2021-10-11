package com.houyongju.cloud.service.impl;

import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.DeptMapper;
import com.houyongju.cloud.dao.DeptMapper;
import com.houyongju.cloud.service.DeptService;
import com.houyongju.cloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 20:33
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public int insertDept(Dept dept) {
        return deptMapper.insertDept(dept);
    }

    @Override
    public List<Dept> queryDept(Page page, Dept dept) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, page, dept);
        return deptMapper.queryDept(map);
    }

    @Override
    public int queryDeptCount(Dept dept) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, null, dept);
        return deptMapper.queryDeptCount(map);
    }

    @Override
    public Dept queryDeptById(int deptId) {
        return deptMapper.queryDeptById(deptId);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateDept(dept);
    }

    @Override
    public int deleteDept(int id) {
        return deptMapper.deleteDept(id);
    }
    public void putBeanIntoMap(HashMap<String,Object> map, Page page, Dept dept ){
        if(page != null){
            map.put("limit",page.getLimit());
            map.put("offset",page.getOffset());
        }
        if(dept != null){

            map.put("name", dept.getName());
            map.put("remark", dept.getRemark());
        }
    }
}
