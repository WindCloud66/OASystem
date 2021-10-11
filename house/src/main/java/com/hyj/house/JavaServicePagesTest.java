package com.hyj.house;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HouYongJu
 * @create 2021-09-30 23:35
 */

@RequestMapping("/test/")
public class JavaServicePagesTest {
    private static final String datePattern="yyyy-MM-dd E HH:mm:ss";

    @RequestMapping("/m1.action")
    public String methodOne(HttpServletRequest request){
        request.setAttribute("time", new SimpleDateFormat(datePattern).format(new Date()));
        return "test";
    }
}
