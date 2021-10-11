package com.houyongju.cloud.bean;

import java.util.Date;

/**
 * @author HouYongJu
 * @create 2021-10-08 17:06
 */
public class Employee {
    private Long id;
    private String password;
    private Integer deptId;
    private Integer jobId;
    private String name;
    private String cardId;
    private String phone;
    private String email;
    private Integer sex;
    private String party;
    private String race;
    private String education;
    private Date createDate;
    private String imgName;

        public Employee() {

        }

    public Employee(Long id, String password, int deptId, int jobId, String name, String cardId, String phone, String email, int sex, String party, String race, String education, Date createDate, String imgName) {
        this.id = id;
        this.password = password;
        this.deptId = deptId;
        this.jobId = jobId;
        this.name = name;
        this.cardId = cardId;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.party = party;
        this.race = race;
        this.education = education;
        this.createDate = createDate;
        this.imgName = imgName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeptId() {

        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", deptId=" + deptId +
                ", jobId=" + jobId +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", party='" + party + '\'' +
                ", race='" + race + '\'' +
                ", education='" + education + '\'' +
                ", createDate=" + createDate +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
