package com.ftd.seckill.security.config;

import com.ftd.seckill.security.filter.FtdUsernamePasswordValidationFilter;
import com.ftd.seckill.security.handler.LoginFailureHandler;
import com.ftd.seckill.security.handler.LoginSuccessHandler;
import com.ftd.seckill.security.handler.UnauthEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class FtdSecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

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
                // 添加自定义Filter
                .addFilterBefore(new FtdUsernamePasswordValidationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 访问权限设置
                .authorizeRequests(auth -> {
                    auth.antMatchers("/login/**").permitAll();
                    auth.antMatchers("/demo/**").authenticated();
//                    auth.anyRequest().authenticated();
                })
                // 开启login
                .formLogin(login ->{
                    login.loginPage("/login/toLogin");
                    login.usernameParameter("userEmail");
                    login.loginProcessingUrl("/login/doLogin");
                    login.successHandler(new LoginSuccessHandler());
                    login.failureUrl("/login/toLogin");
                    login.failureHandler(new LoginFailureHandler());
//                    login.defaultSuccessUrl("/index.html");
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
