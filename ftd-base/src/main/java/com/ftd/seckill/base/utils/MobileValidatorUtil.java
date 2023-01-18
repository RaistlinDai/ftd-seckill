package com.ftd.seckill.base.utils;

import com.ftd.seckill.base.annotation.Mobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Mobile Validator
 * Used by Customized Annotation @Mobile
 */
public class MobileValidatorUtil implements ConstraintValidator<Mobile, String> {

    private boolean required = false;
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    @Override
    public void initialize(Mobile constraintAnnotation) {
        boolean required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required || StringUtils.hasText(s))
            return MOBILE_PATTERN.matcher(s).matches();
        else
            return true;
    }
}
