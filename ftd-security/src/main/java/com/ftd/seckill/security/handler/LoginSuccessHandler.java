package com.ftd.seckill.security.handler;

import ch.qos.logback.core.util.TimeUtil;
import com.ftd.seckill.base.utils.TokenUtil;
import com.ftd.seckill.security.entity.SecurityUserDetails;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Login成功处理器
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedisTemplate redisTemplate;

    public LoginSuccessHandler(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获得认证成功的用户信息
        SecurityUserDetails userDetails = (SecurityUserDetails)authentication.getPrincipal();
        // 根据用户名生成token
        String token = TokenUtil.createToken(userDetails.getCurrentUserInfo().getUserName());
        System.out.println("=================> token:" + token);
        // 把用户名称token和用户信息放到redis
        redisTemplate.opsForValue().set(token, userDetails);
        redisTemplate.expire(token, TokenUtil.TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
        // 设置response message
        FtdSecurityResponseHandler.formatServletResponse(response, null);
    }
}
