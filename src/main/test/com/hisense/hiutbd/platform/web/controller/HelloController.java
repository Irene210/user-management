package com.hisense.hiutbd.platform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello1")
    public String hello1() {
        return "success";
    }

}
