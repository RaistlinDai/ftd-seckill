package com.ftd.seckill.ftdservice.entity;

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
 * @since 2023-01-17 12:06:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String permissionId;

    private String permissionName;

    private Integer permissionType;

    private Integer permissionStatus;

    private String permissionValue;

    private String permissionPath;

    private String permissionComp;
}
