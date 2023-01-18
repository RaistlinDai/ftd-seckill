package com.ftd.seckill.base.annotation;

import com.ftd.seckill.base.utils.MobileValidatorUtil;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义注解：验证手机号
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={MobileValidatorUtil.class})
public @interface Mobile {
    boolean required() default true;
    String message() default "Mobile format incorrect";
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default {};
}
