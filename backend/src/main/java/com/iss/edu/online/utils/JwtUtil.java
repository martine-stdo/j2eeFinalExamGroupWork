package com.iss.edu.online.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

/**
 * JwtUtil 类提供了生成和解析 JWT（JSON Web Token）的方法。
 */
@Component
public class JwtUtil {

    // JWT 有效期为14天，以毫秒表示
    public static final long JWT_TTL = 60 * 60 * 1000L * 24 * 14;

    // JWT 密钥，使用 HMAC SHA-256 算法
    public static final SecretKey JWT_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 生成 UUID 字符串。
     *
     * @return UUID 字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 创建 JWT。
     *
     * @param subject  JWT 主题，通常是用户ID
     * @param isAdmin  是否是管理员
     * @return JWT 字符串
     */
    public static String createJWT(String subject, boolean isAdmin) {
        // 调用私有方法，获取 JwtBuilder 实例
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID(), isAdmin, JWT_KEY);
        return builder.compact();
    }

    /**
     * 获取 JwtBuilder 实例。
     *
     * @param subject    JWT 主题，通常是用户ID
     * @param ttlMillis  JWT 有效期，以毫秒表示
     * @param uuid       JWT 唯一标识符
     * @param isAdmin    是否是管理员
     * @param secretKey  JWT 密钥
     * @return JwtBuilder 实例
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid, boolean isAdmin, SecretKey secretKey) {
        // 指定使用 HMAC SHA-256 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 获取当前时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 如果未指定有效期，则使用默认值 JWT_TTL
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        // 计算过期时间
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        // 构建 JWT
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("sg")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate)
                .claim("isAdmin", isAdmin);
    }

    /**
     * 获取通用密钥。
     *
     * @return 通用密钥
     */
    public static SecretKey generalKey() {
        return JwtUtil.JWT_KEY;
    }

    /**
     * 解析 JWT。
     *
     * @param jwt  JWT 字符串
     * @return 包含 JWT Claims 的对象
     * @throws ExpiredJwtException      如果 JWT 过期
     * @throws UnsupportedJwtException  如果 JWT 不受支持
     * @throws MalformedJwtException    如果 JWT 格式错误
     * @throws SignatureException       如果 JWT 签名错误
     * @throws IllegalArgumentException 如果参数无效
     */
    public static Claims parseJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        SecretKey secretKey = generalKey();

        // 使用 builder 模式构建 JwtParser 实例，设置签名密钥
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
