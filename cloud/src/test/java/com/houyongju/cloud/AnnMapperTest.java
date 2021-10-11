package com.houyongju.cloud;


import com.houyongju.cloud.bean.Announcement;
import com.houyongju.cloud.bean.Dept;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.AnnouncementMapper;
import com.houyongju.cloud.dao.DeptMapper;
import com.houyongju.cloud.service.AnnouncementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author HouYongJu
 * @create 2021-10-01 18:21
 */
@SpringBootTest
public class AnnMapperTest {
    @Autowired
    private AnnouncementService AnnouncementService;


    @Test
    public void testSelectDept(){

//        int insertAnnouncement(Announcement announcement);++
//        public List<Announcement> queryAnnouncement(Page page, Announcement announcement);++++
//        public int queryAnnouncementCount(Announcement announcement);+++
//        public Announcement queryAnnouncementById(int announcementId);+++
//        int updateAnnouncement(Announcement announcement);+++
//        public int deleteAnnouncement(int id);+++

//        Date date = new Date(System.currentTimeMillis());
//        Announcement announcement = new Announcement("疫情防控通知","切实落实各项防疫要求",1);
//        announcement.setId(9);
//        announcement.setUid(2);
//        Page page = new Page();
//                page.setCurrent(1);
//                page.setLimit(10);
//        List<Announcement> announcements = AnnouncementService.queryAnnouncement(page,null);
//        System.out.println(announcements);
        int i = AnnouncementService.queryAnnouncementCount(null);
        System.out.println(i);
        Announcement announcement = AnnouncementService.queryAnnouncementById(2);
        System.out.println(announcement);
    }



}
