package com.ftd.seckill.base.utils;

import org.junit.jupiter.api.Test;

public class ValidatorUtilTest {

    @Test
    public void isMobileTest(){
        MobileValidatorUtil util = new MobileValidatorUtil();
        assert util.isValid("13636568151", null) : "13636568151 case failed";
        assert !util.isValid("14567843432", null) : "14567843432 case failed";
        assert !util.isValid("136554677890", null) : "136554677890 case failed";
        assert !util.isValid("1365546778", null) : "1365546778 case failed";
    }

    @Test
    public void isEmailTest(){
        EmailValidatorUtil util = new EmailValidatorUtil();
        assert util.isValid("ftd@129.com") : "ftd@129.com case failed";
        assert !util.isValid("@127.com") : "@127.com case failed";
        assert !util.isValid("ftd@127") : "ftd@127 case failed";
        assert !util.isValid("1365546778") : "1365546778 case failed";
    }
}
