package com.iss.edu.online.controller.UserController;

import com.iss.edu.online.Service.RegisterService;
import com.iss.edu.online.common.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/register")
    public ResultResponse register(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String confirmPassword = map.get("confirmPassword");
        return ResultResponse.success(registerService.register(username, password, confirmPassword));
    }
}
