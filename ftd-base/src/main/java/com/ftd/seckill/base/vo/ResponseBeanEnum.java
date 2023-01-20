package com.ftd.seckill.base.vo;

import lombok.*;

/**
 * 公共返回对象美剧
 */

@ToString
@AllArgsConstructor
@Getter
public enum ResponseBeanEnum {
    SUCCESS(200,"Success"),
    ERROR(500, "Server Side Exception"),
    // Login Validations
    LOGIN_INVALID_ERROR(500210, "Invalid user email or password"),
    LOGIN_EMAIL_ERROR(500211, "Incorrect user email format"),
    LOGIN_EXPIRED_TOKEN_ERROR(500212, "Expired token, please login"),
    LOGIN_PASSWORD_ERROR(500213, "Incorrect password"),
    // Binding Exception
    BINDING_ERROR(500500, "Parameter exception");

    private final Integer code;
    private final String message;
}
