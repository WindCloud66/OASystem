package com.houyongju.cloud.dao;

import com.houyongju.cloud.bean.FileBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-10 19:29
 */
@Mapper
public interface FileMapper {
    // 定义添加操作[入参一个File对象]
    int insertFile(FileBean file);
    // 删除用户
    int deleteFile(int id);
    //     更新用户
    int updateFile(FileBean file);
    //定义查询所有File数据
    List<FileBean> queryFile(HashMap map);
    //定义查询File总数据记录
    int queryFileCount(HashMap map);
    //--定义File的根据id查询的方法（入参id）
    FileBean queryFileById(int id);
    //--定义File的登录查询方法（入参登录名和密码）
//    File queryFileLogin(String loginName, String password);
}
