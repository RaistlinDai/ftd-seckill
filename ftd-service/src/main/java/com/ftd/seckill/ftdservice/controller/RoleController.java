package com.ftd.seckill.ftdservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ftd.seckill.ftdservice.service.RoleService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ftd
 * @since 2023-01-17 12:06:22
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService RoleService;

}
