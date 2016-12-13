package com.hisense.hiutbd.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value="/")
    public String home(){
        System.out.print("homeeee-----------");
        return "index";
    }

    @RequestMapping(value="/test")
    public String test(){
        System.out.print("homeeee-----------");
        return "index";
    }

}
