package com.ftd.seckill.base.utils;

import com.ftd.seckill.base.annotation.Mobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Email Validator
 * Used by Customized Annotation @Mobile
 */
public class EmailValidatorUtil{

    private boolean required = false;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    public static boolean isValid(String s) {
        if (StringUtils.hasText(s))
            return EMAIL_PATTERN.matcher(s).matches();
        else
            return true;
    }
}
