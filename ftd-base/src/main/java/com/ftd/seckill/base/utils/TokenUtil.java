package com.ftd.seckill.base.utils;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class TokenUtil {

    // token 有效时长：4小时
    public static final long TOKEN_EXPIRATION = 4*60*60*1000;
    // 编码密钥
    private static final String JWT_SIGN_KEY = "raistlindc_jwt_token_json_sign_key";
    private static String BASE64_SECURITY = Base64.getEncoder().encodeToString(JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8));

    /**
     * 使用JWT根据用户名生成token
     * @param username
     * @return
     */
    public static String createToken(String username){
        // 生成签名密钥
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
        // 生成签名
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, signingKey).compressWith(CompressionCodecs.GZIP).compact();
    }

    /**
     * 根据token获得用户信息
     * @param token
     * @return
     */
    public static String getUserInfoFromToken(String token) {
        String userInfo = null;

        try {
            userInfo = Jwts.parser().setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY))
                    .parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException ee) {
            return null;
        }
        return userInfo;
    }
}
