package com.ftd.seckill.security.handler;

import com.ftd.seckill.base.vo.ResponseBeanEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login失败处理器
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResponseErrorHandler.formatServletResponse(response, ResponseBeanEnum.LOGIN_INVALID_ERROR);
    }
}
