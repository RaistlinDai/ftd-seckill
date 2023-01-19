package com.ftd.seckill.security.handler;

import com.ftd.seckill.base.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FtdPasswordEncoder implements PasswordEncoder {
    public FtdPasswordEncoder(){
        this(-1);
    }

    public FtdPasswordEncoder(int strength) {}

    /**
     * Password进行MD5加密
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        log.info(" ===============> encode: {}", rawPassword.toString());
        return MD5Util.MD5WithSalt(rawPassword.toString());
    }

    /**
     * Password比对
     * @param rawPassword the raw password to encode and match
     * @param encodedPassword the encoded password from storage to compare with
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.info(" ===============> rawPassword: {}", rawPassword.toString());
        log.info(" ===============> encodedPassword: {}", encodedPassword);
        return MD5Util.verifyBackPasswordWithDatabasePassword(rawPassword.toString(), encodedPassword);
    }
}
