package com.ftd.seckill.ftdservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ftd.seckill.ftdservice.service.ISeckillGoodsService;
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
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {

    @Autowired
    private ISeckillGoodsService SeckillGoodsService;

}
