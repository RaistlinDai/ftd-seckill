package com.ftd.seckill.ftdservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Order ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * User ID
     */
    private String userId;

    /**
     * Good ID
     */
    private Long goodsId;

    /**
     * Deliver Address ID
     */
    private Long deliveryAddrId;

    /**
     * Redundant Good Name
     */
    private String goodsName;

    /**
     * Good Count
     */
    private Integer goodsCount;

    /**
     * Good Price
     */
    private BigDecimal goodsPrice;

    /**
     * 1:PC;2:Android;3:IOS
     */
    private Byte orderChannel;

    /**
     * Order Status
     */
    private Byte status;

    /**
     * Create Date
     */
    private LocalDateTime createDate;

    /**
     * Pay Date
     */
    private LocalDateTime payDate;
}
