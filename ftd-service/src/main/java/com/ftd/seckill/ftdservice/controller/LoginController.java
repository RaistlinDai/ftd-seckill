package com.ftd.seckill.ftdservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Slf4j
public class LoginController {

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        log.info("LoginController.toLogin invoked.");
        return "login";
    }

    /**
     * 跳转登出页面
     * @return
     */
    @RequestMapping("/toLogout")
    public String toLogout(){
        log.info("LoginController.toLogout invoked.");
        return "logout";
    }

}
