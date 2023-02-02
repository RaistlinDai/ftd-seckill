package com.ftd.seckill.ftdservice.mapper;

import com.ftd.seckill.ftdservice.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ftd.seckill.ftdservice.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ftd
 * @since 2023-02-01 04:37:45
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVo> findGoodsVo();
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
