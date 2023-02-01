package com.ftd.seckill.ftdservice.vo;

import com.ftd.seckill.ftdservice.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {

    /**
     * Second-Kill Price
     */
    private BigDecimal seckillPrice;

    /**
     * Stock Count
     */
    private Integer stockCount;

    /**
     * Second-Kill Start Time
     */
    private LocalDateTime startDate;

    /**
     * Second-Kill End Time
     */
    private LocalDateTime endDate;
}
