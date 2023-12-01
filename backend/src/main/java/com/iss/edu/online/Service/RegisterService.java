package com.iss.edu.online.Service;

import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.User;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterService {
    @Autowired
private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Map<String, String> register(String username, String password, String confirmPassword) {
        Map<String, String> map = new HashMap<>();

        if (username == null) {
            map.put("error_message","用户名不能为空");
            return map;
        }
        if (password == null || confirmPassword == null) {
            map.put("error_message","密码不能为空");
            return map;
        }

        username = username.trim();
        if (username.length() == 0) {
            map.put("error_message","用户名不能为空");
            return map;
        }

        if (password.length() == 0 || confirmPassword.length() == 0) {
            map.put("error_message","密码不能为空");
            return map;
        }

        if (username.length() > 100) {
            map.put("error_message","用户名长度不能超过100");
            return map;
        }
        if (password.length() > 100 || confirmPassword.length() > 100) {
            map.put("error_message","密码长度不能超过100");
            return map;
        }
        if (!password.equals(confirmPassword)) {
            map.put("error_message","两次密码不一致");
            return map;
        }

//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
        //换成mybatis的查询
        User user = userDao.selectUser(username);
        if (!(user == null)) {
            map.put("error_message","用户名已存在");
            return map;
        }

        String encodePassword = passwordEncoder.encode(password);
        String avator = "https://s1.ax1x.com/2023/03/06/ppZmZ36.jpg";
        User Newuser = new User(10,username,encodePassword,avator,null,null,false);
        userDao.insertUser(Newuser);

        map.put("error_message","success");
        return map;
    }
}
