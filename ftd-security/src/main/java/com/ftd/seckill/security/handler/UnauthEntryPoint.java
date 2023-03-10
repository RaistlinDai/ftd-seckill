package com.ftd.seckill.security.handler;

import com.ftd.seckill.base.vo.ResponseBeanEnum;
import com.ftd.seckill.security.utils.FtdSecurityResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未授权处理类
 */
public class UnauthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println(authException.getMessage());
        FtdSecurityResponseUtil.formatServletResponse(response, ResponseBeanEnum.ERROR);
    }
}
