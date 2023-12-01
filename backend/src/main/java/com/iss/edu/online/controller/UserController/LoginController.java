package com.iss.edu.online.controller.UserController;

import com.iss.edu.online.Service.LoginService;
import com.iss.edu.online.common.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/user/token")
    public ResultResponse getToken(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");

        //loginService会返回一个包含用户标识token的map对象，返回给前端
        Map<String, String> ret = loginService.getToken(username, password);
        return ResultResponse.success(ret);
    }

}
