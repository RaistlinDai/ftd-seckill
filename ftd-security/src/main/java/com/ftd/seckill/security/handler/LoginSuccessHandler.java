package com.ftd.seckill.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftd.seckill.base.utils.TokenUtil;
import com.ftd.seckill.security.entity.SecurityUserDetails;
import com.ftd.seckill.security.utils.CookieUtil;
import com.ftd.seckill.security.utils.FtdSecurityResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        // 根据用户代码生成token
        String token = TokenUtil.createToken(userDetails.getCurrentUserInfo().getUserCode());
        //Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(userDetails.getPermissionValueList());
        // 把用户代码和用户信息放到redis
        redisTemplate.opsForHash().put(userDetails.getCurrentUserInfo().getUserCode(), "useName", userDetails.getCurrentUserInfo().getUserName());
        redisTemplate.opsForHash().put(userDetails.getCurrentUserInfo().getUserCode(), "userEmail", userDetails.getCurrentUserInfo().getUserEmail());
        redisTemplate.opsForHash().put(userDetails.getCurrentUserInfo().getUserCode(), "password", userDetails.getCurrentUserInfo().getUserPassword());
        redisTemplate.opsForHash().put(userDetails.getCurrentUserInfo().getUserCode(), "userMobile", userDetails.getCurrentUserInfo().getUserMobile());
        redisTemplate.opsForHash().put(userDetails.getCurrentUserInfo().getUserCode(), "permissions", jsonString);
        redisTemplate.expire(userDetails.getCurrentUserInfo().getUserCode(), TokenUtil.TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
        // 把token放到cookie
        CookieUtil.setCookie(request, response, "user_ticket", token);

        // 设置response message
        FtdSecurityResponseUtil.formatServletResponse(response, null);
    }
}
