package com.ftd.seckill.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @return
     */
    public static ResponseBean success(Object obj){
        return new ResponseBean(ResponseBeanEnum.SUCCESS.getCode(), ResponseBeanEnum.SUCCESS.getMessage(), obj);
    }

    /**
     * 成功返回结果
     * @return
     */
    public static ResponseBean success(){
        return success(null);
    }

    /**
     * 失败返回结果
     * @return
     */
    public static ResponseBean error(ResponseBeanEnum responseBeanEnum, Object obj) {
        return new ResponseBean(responseBeanEnum.getCode(), responseBeanEnum.getMessage(), obj);
    }

    /**
     * 失败返回结果
     * @return
     */
    public static ResponseBean error(ResponseBeanEnum responseBeanEnum) {
        return error(responseBeanEnum, null);
    }
}
