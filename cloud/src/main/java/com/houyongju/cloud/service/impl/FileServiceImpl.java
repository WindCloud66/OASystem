package com.houyongju.cloud.service.impl;

import com.houyongju.cloud.bean.FileBean;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.FileMapper;
import com.houyongju.cloud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-10 19:38
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public int insertFile(FileBean file) {

        return fileMapper.insertFile(file);
    }

    @Override
    public List<FileBean> queryFile(Page page, FileBean file) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, page, file);
        return fileMapper.queryFile(map);
    }

    @Override
    public int queryFileCount(FileBean file) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, null, file);
        return fileMapper.queryFileCount(null);
    }

    @Override
    public FileBean queryFileById(int fileId) {
        return fileMapper.queryFileById(fileId);
    }

    @Override
    public int updateFile(FileBean file) {
        return fileMapper.updateFile(file);
    }

    @Override
    public int deleteFile(int id) {
        return fileMapper.deleteFile(id);
    }

    public void putBeanIntoMap(HashMap<String,Object> map, Page page, FileBean file ){
        if(page != null){
            map.put("limit",page.getLimit());
            map.put("offset",page.getOffset());
        }
        if(file != null){
            map.put("filename",file.getFilename());
        }

    }
}
