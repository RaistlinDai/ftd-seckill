package com.ftd.seckill.ftdservice.service;

import com.ftd.seckill.ftdservice.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ftd.seckill.ftdservice.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ftd
 * @since 2023-02-01 04:37:45
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();
}
