package com.houyongju.cloud.bean;

/**
 * @author HouYongJu
 * @create 2021-10-08 18:59
 */
public class Job {

    private int id;
    private String name;
    private String remark;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Job(int id, String name, String remark) {
        super();
        this.id = id;
        this.name = name;
        this.remark = remark;
    }
    public Job() {
        super();
    }
    @Override
    public String toString() {
        return "Job [id=" + id + ", name=" + name + ", remark=" + remark + "]";
    }





}
