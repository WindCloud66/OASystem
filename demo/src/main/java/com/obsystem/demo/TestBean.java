package com.obsystem.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HouYongJu
 * @create 2021-09-30 22:23
 */
@Controller
public class TestBean {
    @RequestMapping("/index")
    public String TestDemo(){

        return "index";
    }
}
