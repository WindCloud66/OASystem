package com.houyongju.cloud.service.impl;

import com.houyongju.cloud.bean.Announcement;
import com.houyongju.cloud.bean.Employee;
import com.houyongju.cloud.bean.Page;
import com.houyongju.cloud.dao.AnnouncementMapper;
import com.houyongju.cloud.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-09 18:41
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announceMapper;
    @Override
    public int insertAnnouncement(Announcement announcement) {
        return announceMapper.insertAnnouncement(announcement);
    }

    @Override
    public List<Announcement> queryAnnouncement(Page page, Announcement announcement) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, page, announcement);
        return announceMapper.queryAnnouncement(map);
    }

    @Override
    public int queryAnnouncementCount(Announcement announcement) {
        HashMap<String,Object> map = new HashMap<>();
        putBeanIntoMap(map, null, announcement);
        return announceMapper.queryAnnouncementCount(map);
    }

    @Override
    public Announcement queryAnnouncementById(int announcementId) {
        return announceMapper.queryAnnouncementById(announcementId);
    }

    @Override
    public int updateAnnouncement(Announcement announcement) {
        return announceMapper.updateAnnouncement(announcement);
    }

    @Override
    public int deleteAnnouncement(int id) {
        return announceMapper.deleteAnnouncement(id);
    }
    public void putBeanIntoMap(HashMap<String,Object> map, Page page, Announcement announcement ){
        if(page != null){
            map.put("limit",page.getLimit());
            map.put("offset",page.getOffset());
        }
        if(announcement != null){
            map.put("title", announcement.getTitle());
            map.put("content", announcement.getContent());
            map.put("uid", announcement.getUid());
        }
    }
}
