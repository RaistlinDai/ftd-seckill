package com.ftd.seckill.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userCode;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String roleId;
    private LocalDateTime registerDate;
    private LocalDateTime lastLoginDate;
    private Integer loginCount;
    private Long userMobile;
}
