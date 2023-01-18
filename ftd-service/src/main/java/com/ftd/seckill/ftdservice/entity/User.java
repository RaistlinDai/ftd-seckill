package com.ftd.seckill.ftdservice.entity;

import java.io.Serializable;
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
 * @since 2023-01-17 12:06:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userCode;

    private String userEmail;

    private String userName;

    /**
     * MD5(MD5(password+salt)+salt)
     */
    private String userPassword;

    private String roleId;

    private LocalDateTime registerDate;

    private LocalDateTime lastLoginDate;

    private Integer loginCount;

    private Long userMobile;
}
