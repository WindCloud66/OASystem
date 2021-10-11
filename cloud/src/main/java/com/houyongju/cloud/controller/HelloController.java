package com.houyongju.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author HouYongJu
 * @create 2021-10-07 20:43
 */
@Controller
public class HelloController {


    @RequestMapping(path = "/index", method = RequestMethod.GET )
    public String index(Model model){

        return "index";
    }

    @RequestMapping(path = "/userIndex", method = RequestMethod.GET )
    public String userIndex(Model model){

        return "userList";
    }
}
