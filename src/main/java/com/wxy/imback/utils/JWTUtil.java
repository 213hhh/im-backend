package com.wxy.imback.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.wxy.imback.model.LoginUser;

import java.util.Date;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
public class JWTUtil {

    /**
     * token过期时间 7天
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * 加密秘钥
     */
    private static final String SECRET = "zhige666";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "wxy888";

    /**
     * subject
     */
    private static final String SUBJECT = "im";


    /**
     * 根据用户信息，生成令牌
     *
     * @param loginUser
     * @return
     */
    public static String geneJsonWebToken(LoginUser loginUser) {
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("user_id", loginUser.getUserId())
                .claim("email", loginUser.getEmail())
                .claim("mobile", loginUser.getMobile())
                .claim("user_name", loginUser.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }


    /**
     * 校验token的方法
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

}
