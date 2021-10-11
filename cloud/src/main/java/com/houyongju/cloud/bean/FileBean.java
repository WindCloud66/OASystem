package com.houyongju.cloud.bean;

import java.util.Date;

/**
 * @author HouYongJu
 * @create 2021-10-10 19:22
 */
public class FileBean {
//    `ID` int NOT NULL AUTO_INCREMENT,
//  `filename` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
//  `REMARK` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
//  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
//            `uploader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    private Integer id;
    private String filename;
    private String remark;
    private Date createTime;
    private Integer uploader;

    public FileBean() {
    }

    public FileBean(String filename, String remark, Integer uploader) {
        this.filename = filename;
        this.remark = remark;
        this.uploader = uploader;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUploader() {
        return uploader;
    }

    public void setUploader(Integer uploader) {
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", uploader=" + uploader +
                '}';
    }
}
