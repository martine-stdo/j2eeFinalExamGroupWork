package com.iss.edu.online.Service;

import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.User;
import com.iss.edu.online.utils.JwtUtil;
import com.iss.edu.online.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;

    public Map<String, String> getToken(String username, String password) {
        Map<String, String> map = new HashMap<>();

        try {
            // 创建一个包含用户名和密码的UsernamePasswordAuthenticationToken对象
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            // authenticationManager是一个用于验证用户的认证管理器，传入的参数就是刚才的UsernamePasswordAuthenticationToken对象，包含用户信息
            // 调用authenticationManager.authenticate(authenticationToken)方法时，
            // Spring Security会使用上述的UserDetailsService接口实现类的loadUserByUsername方法进行具体的验证比较
            // 比较成功会返回一个被更新且包含用户身份验证信息的Authentication对象，其实就是UserDetailsImpl对象
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            // 通过authenticate.getPrincipal()获取到其中包含用户信息的UserDetailsImpl对象
            UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
            // UserDetailsImpl类中现在属性user已经被赋值了，现在需要拿到这个信息，通过get方法
            User user = userDetails.getUser();
            String jwt = JwtUtil.createJWT(user.getId().toString(), user.isAdmin());
            map.put("error_message", "success");
            map.put("token", jwt);
        } catch (Exception e) {
            // 用户不存在的情况
            map.put("error_message", "failed");
        }

        return map;
    }
}
