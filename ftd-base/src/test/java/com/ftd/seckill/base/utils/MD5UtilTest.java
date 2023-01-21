package com.ftd.seckill.base.utils;

import org.junit.jupiter.api.Test;

public class MD5UtilTest {

    @Test
    public void inputPasswordToDatabasePasswordTest(){
        String p1 = MD5Util.inputPasswordToBackPassword("123456");
        String p2 = MD5Util.backPasswordToDatabasePassword(p1);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(MD5Util.verifyBackPasswordWithDatabasePassword(p1, p2));
    }
}
