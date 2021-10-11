package com.houyongju.cloud;

import com.houyongju.cloud.bean.FileBean;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-10 19:43
 */
@SpringBootTest
public class FileBeanTest {
    @Autowired
    private FileService fileService;
    @Test
    public void test(){
//        File file = new File("这是一个文件", "通知级别", 2);
//        fileService.insertFile(file);
//        File fileById = fileService.queryFileById(1);
//        fileById.setUploader(2);;
//        fileService.updateFile(fileById);
        Page page = new Page();
        page.setCurrent(1);
        page.setLimit(5);
        List<FileBean> files = fileService.queryFile(page, null);
        int fileCount = fileService.queryFileCount(null);
        System.out.println(files);
        System.out.println(fileCount);
//        fileService.deleteFile(1);

    }





//    //
//    public int deleteFile(int id);
}
