package com.iss.edu.online.controller.UserController;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.User;
import com.iss.edu.online.utils.CheckAdmin;
import com.iss.edu.online.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShowUserInfo {

    @Autowired
    private CheckAdmin checkAdmin;

    @Autowired
    private UserDao userDao;

    // GET 请求，获取用户信息
    @GetMapping ("/user/info")
    public ResultResponse userInfo(@RequestHeader("Access_token") String authorization) throws Exception {
        // 从请求头中提取 JWT
        String jwt = checkAdmin.extractJwt(authorization);

        // 解析 JWT，获取用户ID
        Claims claims = JwtUtil.parseJWT(jwt);
        String userId = claims.getSubject();
        int id = Integer.parseInt(userId);

        // 通过用户ID查询用户信息
        User user =  userDao.selectById(id);

        // 为了保护用户隐私，将密码字段设置为null
        user.setPassword(null);

        // 返回成功响应，携带用户信息
        return ResultResponse.success(user);
    }
}
