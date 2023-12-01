package com.iss.edu.online.configuration.filter;

import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.User;
import com.iss.edu.online.utils.JwtUtil;
import com.iss.edu.online.utils.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理JWT认证令牌的自定义过滤器。
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDao userDao;

    /**
     * 过滤传入请求以提取和验证JWT令牌。
     *
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException 如果在过滤期间发生异常。
     * @throws IOException      如果在过滤期间发生I/O异常。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中提取JWT令牌
        String token = request.getHeader("Access_token");

        // 如果令牌不存在或不以"Bearer "开头，则继续下一个过滤器
        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 去除"Bearer "前缀以获取实际令牌值
        token = token.substring(7);

        String userid;
        try {
            // 解析JWT令牌以提取声明
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            // 如果在令牌解析期间发生异常，则抛出运行时异常
            throw new RuntimeException(e);
        }

        // 根据从令牌中提取的用户ID从数据库中检索用户信息
        User user = userDao.selectById(Integer.parseInt(userid));

        // 如果找不到用户，则抛出运行时异常，指示用户未登录
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }

        // 使用检索到的用户信息创建UserDetailsImpl对象
        UserDetailsImpl loginUser = new UserDetailsImpl(user);

        // 使用UserDetailsImpl对象创建认证令牌
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);

        // 在SecurityContextHolder中设置认证令牌
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 继续处理过滤器链中的下一个过滤器
        filterChain.doFilter(request, response);
    }
}
