package com.houyongju.cloud.controller;

import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.bean.User;
import com.houyongju.cloud.service.UserService;
import com.houyongju.cloud.util.OaSystemUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HouYongJu
 * @create 2021-10-07 22:54
 */
@Controller

public class UserController {
    @Autowired
    private UserService userService;

    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model){

        return "/login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST )
    public String login(User user, Model model, HttpServletRequest request){
        String loginname = user.getLoginname();
        String password = user.getPassword();
        if(StringUtils.isBlank(loginname)){
            return "/login";
        }
        if(StringUtils.isBlank(password)){
            return "login";
        }
        User userLogin = userService.queryUserLogin(loginname, password);
        if (userLogin != null){
            request.getSession().setAttribute("userLogin",user);
            return "redirect:/index";
        }else{
            return "login";
        }

    }





//(searchloginname=${searchloginname})(searchUserName=${searchUserName})(searchStatus=${searchStatus})
    @RequestMapping(path = "/user/list", method = RequestMethod.GET)
    public String getUserList( @RequestParam(value = "searchloginname", required = false)String searchloginname,
                               @RequestParam(value = "searchUserName", required = false)String searchUserName,
                               @RequestParam(value = "searchStatus", required = false)String searchStatus,Model model, Page page){

        User user = new User();

        if(StringUtils.isNotBlank(searchloginname)){
            user.setLoginname(searchloginname);
        }
        if(StringUtils.isNotBlank(searchUserName)){
            user.setUsername(searchUserName);
        }
        if(StringUtils.isNotBlank(searchStatus)){
            user.setStatus(Integer.parseInt(searchStatus));
        }else{
            user.setStatus(-1);
        }



        // 用户分页信息
        page.setLimit(5);
        page.setPath("/user/list/");
        page.setRows(userService.queryUserCount(user));
        // 用户列表
        List<User> userList = userService.queryUser(page,user);
        model.addAttribute("userList", userList);
        model.addAttribute("searchloginname", user.getLoginname());
        model.addAttribute("searchUserName", user.getUsername());
        model.addAttribute("searchStatus", user.getStatus());

        return "/userList";
    }
    @RequestMapping(path = "/user/insertUser", method = RequestMethod.GET)
    public String getAddUser( Model model){

        return "/userInsert";
    }

    @RequestMapping(path = "/user/insertUser", method = RequestMethod.POST)
    public String addUser(User user, MultipartFile filePart, Model model){
        if(filePart == null || filePart.isEmpty()){
            // 添加当前用户的头像的路径(web访问路径)
            // http://localhost:8080/community/user/header/xxx.png
            String headerUrl = domain + "/user/image/default.png";
            user.setImgname(headerUrl);
            userService.insertUser(user);
            return "redirect:/user/list";
        }
        // 获取文件源名字
        String filename = filePart.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        if(StringUtils.isBlank(suffix)){
            model.addAttribute("error", "文件格式不正确");
            return "/userInsert";
        }

        // 生成随机文件名
        String fileName = OaSystemUtil.generateUUID() + suffix;
        // 确定文件存放路径
        File dest = new File(uploadPath + "/" + fileName);
        try {
            filePart.transferTo(dest);
        } catch (IOException e) {
            System.out.println("上传文件失败:" + e.getMessage());
            throw new RuntimeException("上传文件失败, 服务器发送异常", e);
        }

        // 添加当前用户的头像的路径(web访问路径)
        // http://localhost:8080/community/user/header/xxx.png
        String headerUrl = domain + "/user/image/" + fileName;
        user.setImgname(headerUrl);


        userService.insertUser(user);
        return "redirect:/user/list";
    }
    @RequestMapping(path = "/user/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(value = "searchloginname", required = false)String searchloginname,
                             @RequestParam(value = "searchUserName", required = false)String searchUserName,
                             @RequestParam(value = "searchStatus", required = false)String searchStatus,int userId, Model model){
        User user = new User();

        if(StringUtils.isNotBlank(searchloginname)){
            user.setLoginname(searchloginname);
        }
        if(StringUtils.isNotBlank(searchUserName)){
            user.setLoginname(searchUserName);
        }
        if(StringUtils.isNotBlank(searchStatus)){
            user.setStatus(Integer.parseInt(searchStatus));
        }else{
            user.setStatus(-1);
        }
        userService.deleteUser(userId);
        // 用户分页信息
        int rows = userService.queryUserCount(user);
        int limit = 5;
        int current = rows % limit == 0 ? rows / limit : rows / limit + 1;
        Page page = new Page();
        page.setLimit(5);
        page.setCurrent(current);
        page.setPath("/user/list/");
        page.setRows(rows);
        model.addAttribute("msg",true);
        model.addAttribute("page",page);

        return "/userList";
    }
}
