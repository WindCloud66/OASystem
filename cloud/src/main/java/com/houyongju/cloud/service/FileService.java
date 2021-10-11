package com.houyongju.cloud.service;

import com.houyongju.cloud.bean.FileBean;
import com.houyongju.cloud.bean.Page;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 19:14
 */
public interface FileService {

    // 定义添加操作[入参一个File对象]
    int insertFile(FileBean file);
    // 查询符合条件
    public List<FileBean> queryFile(Page page, FileBean file);

    //定义查询File总数据记录
    public int queryFileCount(FileBean file);
    // 通过id查询员工
    public FileBean queryFileById(int fileId);
    // 更新
    int updateFile(FileBean file);
    //
    public int deleteFile(int id);
}
