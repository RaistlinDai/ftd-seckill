package com.ftd.seckill.security.handler;

import com.ftd.seckill.base.utils.TokenUtil;
import com.ftd.seckill.security.utils.CookieUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

public class FtdLogoutHandler implements LogoutHandler {
    private RedisTemplate redisTemplate;

    public FtdLogoutHandler(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 获得token
        String token = CookieUtil.getCookieValue(request, "user_ticket");
        if (StringUtils.hasText(token)) {
            // 从token中解析用户代码
            String userCode = TokenUtil.getUserInfoFromToken(token);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            // token未过期
            if (StringUtils.hasText(userCode)) {
                // 从Redis中删除token
                if (redisTemplate.opsForHash().hasKey(userCode, "userEmail"))
                    redisTemplate.delete(userCode);
            }
        }
    }
}
