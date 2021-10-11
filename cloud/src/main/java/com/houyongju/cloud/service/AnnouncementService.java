package com.houyongju.cloud.service;

import com.houyongju.cloud.bean.Announcement;
import com.houyongju.cloud.bean.Page;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 19:14
 */
public interface AnnouncementService {

    // 定义添加操作[入参一个Announcement对象]
    int insertAnnouncement(Announcement announcement);
    // 查询符合条件
    public List<Announcement> queryAnnouncement(Page page, Announcement announcement);

    //定义查询Announcement总数据记录
    public int queryAnnouncementCount(Announcement announcement);
    // 通过id查询员工
    public Announcement queryAnnouncementById(int announcementId);
    // 更新
    int updateAnnouncement(Announcement announcement);
    //
    public int deleteAnnouncement(int id);
}
