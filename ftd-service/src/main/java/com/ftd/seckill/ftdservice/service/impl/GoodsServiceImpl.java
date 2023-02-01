package com.ftd.seckill.ftdservice.service.impl;

import com.ftd.seckill.ftdservice.entity.Goods;
import com.ftd.seckill.ftdservice.mapper.GoodsMapper;
import com.ftd.seckill.ftdservice.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftd.seckill.ftdservice.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ftd
 * @since 2023-02-01 04:37:45
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 获取商品列表
     * @return
     */
    public List<GoodsVo> findGoodsVo(){
        return goodsMapper.findGoodsVo();
    }
}
