package com.ftd.seckill.ftdservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("seckill_orders")
public class SeckillOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Second-Kill Order ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * User ID
     */
    private String userId;

    /**
     * Order ID
     */
    private Long orderId;

    /**
     * Good ID
     */
    private Long goodId;
}
