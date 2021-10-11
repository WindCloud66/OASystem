package com.houyongju.cloud.dao;

import com.houyongju.cloud.bean.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-10-08 17:12
 */
@Mapper
public interface AnnouncementMapper {
    // 定义添加操作[入参一个Announcement对象]
    int insertAnnouncement(Announcement announcement);
    // 删除用户
    int deleteAnnouncement(int id);
//     更新用户
    int updateAnnouncement(Announcement announcement);
    //定义查询所有Announcement数据
    List<Announcement> queryAnnouncement(HashMap map);
    //定义查询Announcement总数据记录
    int queryAnnouncementCount(HashMap map);
    //--定义Announcement的根据id查询的方法（入参id）
    Announcement queryAnnouncementById(int id);
    //--定义Announcement的登录查询方法（入参登录名和密码）
//    Announcement queryAnnouncementLogin(String loginName, String password);
}