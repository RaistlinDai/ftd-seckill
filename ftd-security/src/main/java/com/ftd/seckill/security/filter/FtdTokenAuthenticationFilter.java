package com.ftd.seckill.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftd.seckill.base.utils.TokenUtil;
import com.ftd.seckill.base.vo.ResponseBeanEnum;
import com.ftd.seckill.security.entity.SecurityUserDetails;
import com.ftd.seckill.security.utils.CookieUtil;
import com.ftd.seckill.security.utils.FtdSecurityResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class FtdTokenAuthenticationFilter extends GenericFilterBean {
    private RedisTemplate redisTemplate;

    public FtdTokenAuthenticationFilter(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 获取当前认证成功的用户权限信息
        UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
        log.info("=============xxxxx " + authRequest);
        // 判断如果有权限信息，则放入Context
        if (authRequest != null){
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        chain.doFilter(request, response);
    }

    /**
     * Redis中获取权限列表
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // 从header中获取token
        String token = CookieUtil.getCookieValue(request, "user_ticket");
        if (StringUtils.hasText(token)){
            // 从token中解析用户代码
            String userCode = TokenUtil.getUserInfoFromToken(token);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            // token过期
            if (!StringUtils.hasText(userCode))
                return null;

            // 从Redis中获取权限列表
            String userEmail = (String)redisTemplate.opsForHash().get(userCode, "userEmail");
            String jsonString = (String)redisTemplate.opsForHash().get(userCode, "permissions");
            // Redis数据丢失
            if (!StringUtils.hasText(userEmail) || !StringUtils.hasText(jsonString))
                return null;

            try {
                //Creating the ObjectMapper object
                ObjectMapper mapper = new ObjectMapper();
                ArrayList<String> permissions = mapper.readValue(jsonString, ArrayList.class);
                // 获得用户权限列表
                for (String permissionValue : permissions)
                    authorities.add(new SimpleGrantedAuthority(permissionValue));
            } catch (JsonProcessingException e) {
                return null;
            }
            return new UsernamePasswordAuthenticationToken(userEmail, token, authorities);
        }
        return null;
    }
}
