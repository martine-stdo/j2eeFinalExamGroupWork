package com.iss.edu.online.Service;

import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.User;
import com.iss.edu.online.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //定义具体的验证规则
        User user = userDao.selectUser(username);//通过username去数据库查询用户
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);//返回一个UserDetailsImpl实例 UserDetailsImpl实现了UserDetails接口
    }
}
