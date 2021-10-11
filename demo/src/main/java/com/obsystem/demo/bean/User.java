package com.obsystem.demo.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author HouYongJu
 * @create 2021-10-01 9:19
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private Date createTime;

}