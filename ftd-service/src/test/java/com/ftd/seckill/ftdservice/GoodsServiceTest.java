package com.ftd.seckill.ftdservice;

import com.ftd.seckill.ftdservice.service.IGoodsService;
import com.ftd.seckill.ftdservice.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class GoodsServiceTest {

    @Autowired
    private IGoodsService goodsService;

    @Test
    public void findGoodsVoTestCase001(){
        List<GoodsVo> vos = goodsService.findGoodsVo();
        for (GoodsVo vo : vos){
            log.info("================> GoodsVo:" + vo.getGoodsName());
        }

    }


}
