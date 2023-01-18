package com.ftd.seckill.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 登录参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    @NotEmpty(message = "User email cannot be empty")
    @Email(message = "Invalid email format")
    private String userEmail;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min=32, max=32)
    private String password;
}
