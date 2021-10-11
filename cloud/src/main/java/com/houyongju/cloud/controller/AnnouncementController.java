package com.houyongju.cloud.controller;

import com.houyongju.cloud.bean.Announcement;
import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.bean.User;
import com.houyongju.cloud.service.DeptService;
import com.houyongju.cloud.service.AnnouncementService;
import com.houyongju.cloud.service.JobService;
import com.houyongju.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author HouYongJu
 * @create 2021-10-07 22:54
 */
@Controller

public class AnnouncementController {
    @Autowired
    private AnnouncementService AnnouncementService;

    @Autowired
    private UserService userService;
    @Autowired
    private JobService jobService;

    @Autowired
    private DeptService deptService;
    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;
//(searchloginname=${searchloginname})(searchUserName=${searchUserName})(searchStatus=${searchStatus})
    @RequestMapping(path = "/announcement/list", method = RequestMethod.GET)
    public String getAnnouncementList( Model model, Page page,Announcement searchAnnouncement){
        if(searchAnnouncement == null){
            searchAnnouncement = new Announcement();
        }
        // 用户分页信息
        page.setLimit(5);
        page.setPath("/announcement/list/");
        page.setRows(AnnouncementService.queryAnnouncementCount(searchAnnouncement));

        // 用户列表
        Object selected = null;
        Object selectValue = null;
        List<Announcement> list = AnnouncementService.queryAnnouncement(page, searchAnnouncement);
        List<Map<String,Object>> annListVo = new ArrayList<>();
        List<String> announcementAttr = new ArrayList<>();
        Field[] fields = searchAnnouncement.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            String fieldName =  field.getName();
            if("id".equalsIgnoreCase(fieldName)){
                continue;
            }
            announcementAttr.add(fieldName);
            try {
                Object value = field.get(searchAnnouncement);
                if(value != null){
                    selected = fieldName;
                    selectValue = value;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(!list.isEmpty()){
            for(Announcement announcement: list){
                Map<String, Object> map = new HashMap<>();
                map.put("ann", announcement);
                int uid = announcement.getUid();
                map.put("username",userService.queryUserById(uid).getUsername());
                annListVo.add(map);
            }
        }

        model.addAttribute("annListVo", annListVo);

        model.addAttribute("announcementAttr", announcementAttr);
        model.addAttribute("selected", selected);
        model.addAttribute("selectValue", selectValue);






        return "/announcementList";
    }
    @RequestMapping(path = "/announcement/insertAnnouncement", method = RequestMethod.GET)
    public String getAddAnnouncement( Model model){

        return "/announcementInsert";
    }

    @RequestMapping(path = "/announcement/insertAnnouncement", method = RequestMethod.POST)
    public String addAnnouncement(Announcement announcement, Model model){
        User user = new User();
        user.setId(1);
        announcement.setUid(user.getId());
        AnnouncementService.insertAnnouncement(announcement);
        return "redirect:/announcement/list/";
    }

    @RequestMapping(path = "/announcement/updateAnnouncement/{announcementId}", method = RequestMethod.GET)
    public String updateAnnouncement(@PathVariable(value = "announcementId" ,required = true)int announcementId, Model model){

        Announcement announcement = AnnouncementService.queryAnnouncementById(announcementId);
        model.addAttribute("announcement", announcement);


        return "/announcementUpdate";
    }
    @RequestMapping(path = "/announcement/updateAnnouncement", method = RequestMethod.POST)
    public String updateAnnouncement(Announcement announcement, Model model){
        if(announcement != null)
            AnnouncementService.updateAnnouncement(announcement);

        return "redirect:/announcement/list/";
    }
    @RequestMapping(path = "/announcement/deleteAnnouncement", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(value = "announcementId",required = true) int announcementId, Model model){
        AnnouncementService.deleteAnnouncement(announcementId);

        // 用户分页信息
        int rows = AnnouncementService.queryAnnouncementCount(null);
        int limit = 5;
        int current = rows % limit == 0 ? rows / limit : rows / limit + 1;
        Page page = new Page();
        page.setLimit(5);
        page.setCurrent(current);
        page.setPath("/announcement/list/");
        page.setRows(rows);
        model.addAttribute("page",page);

        return "/AnnouncementList";

    }
}
