package com.ftd.seckill.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftd.seckill.base.vo.ResponseBean;
import com.ftd.seckill.base.vo.ResponseBeanEnum;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpServletResponse错误信息处理器
 */
public class ResponseErrorHandler {
    public static void formatServletResponse (HttpServletResponse response, ResponseBeanEnum responseBeanEnum){
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getWriter(), ResponseBean.error(responseBeanEnum));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
