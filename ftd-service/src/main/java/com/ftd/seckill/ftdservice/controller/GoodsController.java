package com.ftd.seckill.ftdservice.controller;

import com.ftd.seckill.ftdservice.entity.Goods;
import com.ftd.seckill.ftdservice.entity.User;
import com.ftd.seckill.ftdservice.vo.GoodsVo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ftd.seckill.ftdservice.service.IGoodsService;
import org.springframework.stereotype.Controller;

import java.util.Date;

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
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model) {
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goods/goodsList";
    }

    /**
     * 跳转商品详情页
     * @param model
     * @return
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, @PathVariable Long goodsId) {
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        // 秒杀状态
        int seckillState = 0;
        // 秒杀倒计时
        int remainSeconds = 0;

        // 秒杀倒计时
        if (nowDate.before(startDate)) {
            remainSeconds = (int)((startDate.getTime() - nowDate.getTime()) / 1000);
        }
        // 秒杀结束
        else if (nowDate.after(endDate)) {
            seckillState = 2;
            remainSeconds = -1;
        }
        // 秒杀进行中
        else{
            seckillState = 1;
        }

        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("secSkillStatus", seckillState);
        model.addAttribute("goods", goodsVo);
        return "goods/goodsDetails";
    }
}
