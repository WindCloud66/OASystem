package com.oa.bean;

//针对user_inf的Java实体类
public class User {
    //    --私有化的属性
    private int id;
    private String loginname;
    private String password;
    private int status;
    private String createdate;
    private String username;
    private String imgname;

    //    --公有化的get、set方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    //    --构造器【无参、有参】
    public User() {
    }

    public User(int id, String loginname, String password, int status, String createdate, String username, String imgname) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.status = status;
        this.createdate = createdate;
        this.username = username;
        this.imgname = imgname;
    }

    //    --toString方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createdate='" + createdate + '\'' +
                ", username='" + username + '\'' +
                ", imgname='" + imgname + '\'' +
                '}';
    }
}
