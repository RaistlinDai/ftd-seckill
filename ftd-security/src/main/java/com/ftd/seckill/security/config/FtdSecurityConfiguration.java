package com.ftd.seckill.security.config;

import com.ftd.seckill.security.filter.FtdTokenAuthenticationFilter;
import com.ftd.seckill.security.filter.FtdUsernamePasswordValidationFilter;
import com.ftd.seckill.security.handler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FtdSecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加载密码编码器
     * @return
     */
    @Bean
    public FtdPasswordEncoder ftdPasswordEncoder(){
        log.info("FtdSecurityConfiguration.FtdPasswordEncoder invoked.");
        return new FtdPasswordEncoder();
    }

    /**
     * RememberMe token 数据库配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        log.info("FtdSecurityConfiguration.PersistentTokenRepository invoked.");
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动创建token表
        // jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    /**
     * HttpServletRequest 过滤器链
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("FtdSecurityConfiguration.SecurityFilterChain invoked.");
        return http
                // 添加自定义Filter: Token验证过滤器
                .addFilterBefore(new FtdTokenAuthenticationFilter(redisTemplate), UsernamePasswordAuthenticationFilter.class)
                // 添加自定义Filter: 用户名密码格式验证过滤器
                .addFilterBefore(new FtdUsernamePasswordValidationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 访问权限设置
                .authorizeRequests(auth -> {
                    auth.antMatchers("/").permitAll();
                    auth.antMatchers("/index.html").permitAll();
                    auth.antMatchers("/demo/**").authenticated();
//                    auth.antMatchers("/goods/**").hasAuthority("superuser");
//                    auth.anyRequest().authenticated();
                })
                // 开启login
                .formLogin(login ->{
                    login.loginPage("/admin/toLogin");
                    login.usernameParameter("userEmail");
                    login.loginProcessingUrl("/admin/doLogin");
                    login.failureUrl("/admin/toLogin");
                    login.successHandler(new LoginSuccessHandler(redisTemplate));
                    login.failureHandler(new LoginFailureHandler());
                })
                // 开启logout
                .logout(logout -> {
                    logout.logoutUrl("/admin/doLogout");
                    logout.invalidateHttpSession(true);
                    logout.deleteCookies("JSESSIONID", "user_ticket");
                    // handler必须放在deleteCookies后面，否则失效
                    logout.addLogoutHandler(new FtdLogoutHandler(redisTemplate));
                })
                // 开启RememberMe
                .rememberMe(remember ->{
                    remember.rememberMeParameter("rememberMe");
                    remember.tokenValiditySeconds(7*24*60*60); //RememberMe token 过期时间为一周
                    remember.tokenRepository(persistentTokenRepository());
                    remember.userDetailsService(userDetailsService);
                })
                // 未授权访问
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new UnauthEntryPoint()))
                .csrf(csrf -> {
                    csrf.disable();
                })
                .httpBasic()
                .and()
                .build();
    }
}
