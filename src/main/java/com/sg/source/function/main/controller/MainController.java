package com.sg.source.function.main.controller;

import com.sg.source.function.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping("/")
    public String main() throws Exception{
        //String test01.html = mainService.test01.html();
        //System.out.println("test01.html : " + test01.html);
        return "function/user/main/main";
    }
}