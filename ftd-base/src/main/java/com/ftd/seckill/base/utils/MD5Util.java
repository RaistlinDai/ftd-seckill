package com.ftd.seckill.base.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class MD5Util {

    private static final String FTD_SALT ="ftd_seckill_salt";

    /**
     * 用户输入密码传入后端MD5加密(固定加盐)
     * @param inputPassword
     * @return
     */
    public static String inputPasswordToBackPassword(String inputPassword) {
        // 错误取盐
        String str = new StringBuilder().append(FTD_SALT.charAt(5)).append(FTD_SALT.charAt(2))
                .append(inputPassword)
                .append(FTD_SALT.substring(7,11))
                .toString();
        return DigestUtils.md5Hex(str);
    }

    /**
     * 后端加密密码传入数据库MD5加密(随机加盐)
     * @param backPassword
     * @return
     */
    public static String backPasswordToDatabasePassword(String backPassword) {
        return MD5WithSalt(backPassword);
    }

    /**
     * 用户输入密码传入数据库MD5加密
     * @param inputPassword
     * @return
     */
    public static String inputPasswordToDatabasePassword(String inputPassword) {
        String backPassword = inputPasswordToBackPassword(inputPassword);
        return backPasswordToDatabasePassword(backPassword);
    }

    /**
     * 随机加盐MD5加密
     * @param password
     * @return
     */
    public static String MD5WithSalt(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        password = DigestUtils.md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 校验加盐后是否和原文一致
     * @param backPassword
     * @param databasePassword
     * @return
     */
    public static boolean verifyBackPasswordWithDatabasePassword(String backPassword, String databasePassword) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = databasePassword.charAt(i);
            cs1[i / 3 * 2 + 1] = databasePassword.charAt(i + 2);
            cs2[i / 3] = databasePassword.charAt(i + 1);
        }
        String salt = new String(cs2);
        return DigestUtils.md5Hex(backPassword + salt).equals(new String(cs1));
    }
}
