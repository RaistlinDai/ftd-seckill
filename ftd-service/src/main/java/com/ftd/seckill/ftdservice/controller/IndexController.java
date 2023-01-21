package com.ftd.seckill.ftdservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 测试页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/demo/hello")
    public String hello(Model model){
        model.addAttribute("name", "say hi");
        return "hello";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
