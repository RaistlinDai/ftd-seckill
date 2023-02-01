package com.ftd.seckill.ftdservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author ftd
 * @since 2023-02-01 04:37:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("seckill_goods")
public class SeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Second-Kill Good ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Good ID
     */
    private Long goodId;

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
