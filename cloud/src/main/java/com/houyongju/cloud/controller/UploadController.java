package com.houyongju.cloud.controller;


import com.houyongju.cloud.bean.FileBean;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.bean.User;
import com.houyongju.cloud.service.FileService;
import com.houyongju.cloud.service.DeptService;
import com.houyongju.cloud.service.FileService;
import com.houyongju.cloud.service.JobService;
import com.sun.deploy.net.URLEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-07 22:54
 */
@Controller
public class UploadController {

    @Autowired
    private FileService fileService;


    @Autowired
    private DeptService deptService;
    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;
//(searchloginname=${searchloginname})(searchUserName=${searchUserName})(searchStatus=${searchStatus})
    @RequestMapping(path = "/file/list", method = RequestMethod.GET)
    public String getFileList( Model model, Page page, String searchName){
        FileBean fileBean = new FileBean();
        fileBean.setFilename(searchName);
        // 用户分页信息
        page.setLimit(5);
        page.setPath("/file/list");
        page.setRows(fileService.queryFileCount(fileBean));
        // 用户列表
        List<FileBean> list = fileService.queryFile(page, fileBean);

        model.addAttribute("fileList", list);

        model.addAttribute("selectValue", searchName);


        return "/fileInfList";
    }
    @RequestMapping(path = "/file/upload", method = RequestMethod.GET)
    public String getAddFile( Model model){

        return "/fileUpload";
    }

    @RequestMapping(path = "/file/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile file, Model model, HttpServletRequest request){
        // TODO 限制图片大小 限制上传类型
        if(file == null){
            model.addAttribute("error", "您还没有上传文件!");
            return "/fileUpload";
        }

        String fileName = file.getOriginalFilename();


        // 确定文件存放路径
        File dest = new File(uploadPath + "/" + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("上传文件失败, 服务器发送异常", e);
        }
        User user = (User)request.getSession().getAttribute("userLogin");
        FileBean fileBean = new FileBean(fileName,"广大通知",user.getId());

        fileService.insertFile(fileBean);


        return "/fileUpload";


    }
    @RequestMapping(path = "/file/download/{fileId}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileId") int fileId, HttpServletResponse response) {
        // 服务器存放路径
        String fileName = fileService.queryFileById(fileId).getFilename();

        // 响应文件
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(fileName);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setContentType("multipart/form-data");

        try {
            String encodeName = UriUtils.encode(fileName, "utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + encodeName);
        } catch (Exception e) {
            e.printStackTrace();
        }


        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            outputStream = response.getOutputStream();
            fileInputStream = new FileInputStream(uploadPath + "/"+ fileName);
            byte[] buffer = new byte[1024];
            int b = 0;
            while( (b = fileInputStream.read(buffer))!= -1){
                outputStream.write(buffer, 0, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream != null)
                    outputStream.close();
                if(fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
//    @RequestMapping(path = "/file/insertFile", method = RequestMethod.POST)
//    public String addFile(File file, Model model){
//        User user = new User();
//        user.setId(1);
//        file.setUid(user.getId());
//        FileService.insertFile(file);
//        return "redirect:/file/list/";
//    }

//    @RequestMapping(path = "/file/updateFile/{fileId}", method = RequestMethod.GET)
//    public String updateFile(@PathVariable(value = "fileId" ,required = true)int fileId, Model model){
//
//        File file = FileService.queryFileById(fileId);
//        model.addAttribute("file", file);
//
//
//        return "/fileUpdate";
//    }
//    @RequestMapping(path = "/file/updateFile", method = RequestMethod.POST)
//    public String updateFile(File file, Model model){
//        if(file != null)
//            FileService.updateFile(file);
//
//        return "redirect:/file/list/";
//    }
    @RequestMapping(path = "/file/deleteFile", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(value = "fileId",required = true) int fileId, Model model){
        String fileName = fileService.queryFileById(fileId).getFilename();
        File file = new File(uploadPath + "/" + fileName);
        if(file.delete()){
            System.out.println("/Users/pankaj/project directory deleted from Project root directory");
            fileService.deleteFile(fileId);
        }else System.out.println("/Users/pankaj/project directory doesn't exist or not empty");
        // 用户分页信息
        int rows = fileService.queryFileCount(null);
        int limit = 5;
        int current = rows % limit == 0 ? rows / limit : rows / limit + 1;
        Page page = new Page();
        page.setLimit(5);
        page.setCurrent(current);
        page.setPath("/file/list/");
        page.setRows(rows);
        model.addAttribute("page",page);

        return "/fileInfList";

    }
}
