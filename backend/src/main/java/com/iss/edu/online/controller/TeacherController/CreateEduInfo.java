package com.iss.edu.online.controller.TeacherController;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.Course;
import com.iss.edu.online.utils.CheckAdmin;
import com.iss.edu.online.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class CreateEduInfo {

    @Autowired
    private CheckAdmin checkAdmin;

    @Autowired
    private UserDao userDao;

    // POST 请求，创建教育信息
    @PostMapping ("/createEduInfo")
    public ResultResponse createEduInfo(@RequestHeader("Access_token") String authorization, @RequestBody Map<String, String> map) throws Exception {
        // 检查用户是否是管理员
        if (checkAdmin.isAdmin(authorization)) {
            // 从前端传回的参数中获取课程信息
            Course course = new Course();
            course.setCourseName(map.get("courseName"));
            course.setCourseDescription(map.get("courseDescription"));

            // 解析前端传回的日期时间字符串，并设置课程开始和结束时间
            course.setCourseStartTime(LocalDateTime.parse(map.get("courseStartTime"), DateTimeFormatter.ISO_DATE_TIME));
            course.setCourseEndTime(LocalDateTime.parse(map.get("courseEndTime"), DateTimeFormatter.ISO_DATE_TIME));

            // 从JWT中提取用户ID
            String jwt = checkAdmin.extractJwt(authorization);
            Claims claims = JwtUtil.parseJWT(jwt);
            String userId = claims.getSubject();
            course.setTeacherId(Integer.parseInt(userId));

            // 调用 userDao 插入课程信息
            userDao.insertCourse(course);

            // 返回成功响应
            return ResultResponse.success("创建课程成功");
        } else {
            // 用户不是管理员，返回无权限的失败响应
            return ResultResponse.fail(403, "您不是管理员，无法创建课程");
        }
    }
}
