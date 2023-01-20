package com.ftd.seckill.security.filter;

import com.ftd.seckill.base.utils.EmailValidatorUtil;
import com.ftd.seckill.base.vo.ResponseBeanEnum;
import com.ftd.seckill.security.utils.FtdSecurityResponseUtil;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录验证过滤器
 */
public class FtdUsernamePasswordValidationFilter extends GenericFilterBean {

    public static final String USERNAME = "userEmail";
    public static final String PASSWORD = "password";

    /**
     * @Description:用户登录验证方法入口
     * @param :args
     * @return
     * @throws Exception
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);

        // 验证登录邮箱格式
        if (!EmailValidatorUtil.isValid(username))
            FtdSecurityResponseUtil.formatServletResponse(response, ResponseBeanEnum.LOGIN_EMAIL_ERROR);
        else
            chain.doFilter(request, response);
    }

    /**
     * @Description:获取密码
     * @param :args
     * @return
     * @throws Exception
     */
    protected String obtainPassword(ServletRequest request) {
        // TODO Auto-generated method stub
        Object obj = request.getParameter(PASSWORD);
        return null == obj ? "" : obj.toString();
    }

    /**
     * @Description:获取用户名
     * @param :args
     * @return
     * @throws Exception
     */
    protected String obtainUsername(ServletRequest request) {
        // TODO Auto-generated method stub
        Object obj = request.getParameter(USERNAME);
        return null == obj ? "" : obj.toString().trim().toLowerCase();
    }
}
