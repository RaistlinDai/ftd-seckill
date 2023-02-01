package com.ftd.seckill.ftdservice.controller;

import com.ftd.seckill.ftdservice.entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ftd.seckill.ftdservice.service.IGoodsService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ftd
 * @since 2023-02-01 04:37:45
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 跳转商品列表页
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("goodList", goodsService.findGoodsVo());
        return "goodsList";
    }

}
