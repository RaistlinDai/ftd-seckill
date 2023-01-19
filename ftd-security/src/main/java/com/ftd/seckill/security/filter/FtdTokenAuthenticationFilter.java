package com.ftd.seckill.security.filter;

import com.ftd.seckill.base.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class FtdTokenAuthenticationFilter extends BasicAuthenticationFilter {
    private RedisTemplate redisTemplate;

    public FtdTokenAuthenticationFilter(AuthenticationManager authenticationManager,
                                        TokenUtil tokenUtil, RedisTemplate redisTemplate){
        super(authenticationManager);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("TokenAuthenticationFilter.doFilterInternal Invoked.");
        // 获取当前认证成功的用户权限信息
        UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
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
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)){
            // 从token中获取用户名
            String username = TokenUtil.getUserInfoFromToken(token);
            // 从Redis中获取权限列表
            List<String> permissionValueList = (List<String>)redisTemplate.opsForValue().get(username);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String permissionValue: permissionValueList)
                authorities.add(new SimpleGrantedAuthority(permissionValue));

            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        }
        return null;
    }
}
