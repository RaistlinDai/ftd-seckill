package com.ftd.seckill.ftdservice.controller;

import com.ftd.seckill.ftdservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 跳转主页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        log.info("LoginController.index invoked.");
        return "index";
    }

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/login/toLogin")
    public String toLogin(){
        log.info("LoginController.toLogin invoked.");
        return "login";
    }

    /**
     * 跳转登出页面
     * @return
     */
    @RequestMapping("/login/toLogout")
    public String toLogout(){
        log.info("LoginController.toLogout invoked.");
        return "logout";
    }

}
