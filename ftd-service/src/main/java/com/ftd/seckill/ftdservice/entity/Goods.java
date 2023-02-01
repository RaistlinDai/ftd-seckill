package com.ftd.seckill.ftdservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Good ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Good Name
     */
    private String goodsName;

    /**
     * Good Title
     */
    private String goodsTitle;

    /**
     * Good Image
     */
    private String goodsImp;

    /**
     * Good details
     */
    private String goodsDetail;

    /**
     * Good Price
     */
    private BigDecimal goodsPrice;

    /**
     * Good Stock
     */
    private Integer goodsStock;
}
