package com.oa.bean;

/**
 * @author HouYongJu
 * @create 2021-09-29 20:26
 */
public class Dept {
    int id;
    String name;
    String remark;

    public Dept() {
    }

    public Dept(int id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

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
}
