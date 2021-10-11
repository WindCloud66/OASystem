package com.houyongju.cloud.bean;

import java.util.Date;

/**
 * @author HouYongJu
 * @create 2021-10-09 18:00
 */
public class Announcement {
    private Integer id;
    private String title;
    private String content;
    private Date createtime;
    private Integer uid;

    public Announcement() {
    }

    public Announcement(String title, String content , Integer uid) {
        this.title = title;
        this.content = content;
        this.uid = uid;
    }

    public Announcement(Integer id, String title, String content, Date createtime, Integer uid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime='" + createtime + '\'' +
                ", uid=" + uid +
                '}';
    }

}
