package com.iss.edu.online.utils;

import com.iss.edu.online.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public class CheckAdmin {
    public boolean isAdmin(String authorization) {
        try {
            String jwt = extractJwt(authorization); // 从Authorization中提取JWT
            Claims claims = JwtUtil.parseJWT(jwt); // 解析JWT
            return claims.get("isAdmin", Boolean.class); // 获取isAdmin字段的值
        } catch (Exception e) {
            // 处理解析JWT时可能出现的异常
            return false;
        }
    }

    public String extractJwt(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7); // 去掉前缀部分，提取JWT字符串
        }
        return null;
    }
}
