package com.iss.edu.online.controller.TeacherController;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.TeacherDao;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.utils.CheckAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DelEduInfo {
    @Autowired
    private CheckAdmin checkAdmin;
    @Autowired
    private UserDao userDao;

    @Autowired
    private TeacherDao teacherDao;

    @PostMapping ("/delEduInfo")
    public ResultResponse createEduInfo(@RequestHeader("Access_token") String authorization, @RequestParam Integer CourseID) {
        if (checkAdmin.isAdmin(authorization)) {
            //删除课程业务
            //删除学生选课表的信息
            userDao.deleteCourse(CourseID);
            //删除课程表的课程
            teacherDao.deleteCourse(CourseID);
            return ResultResponse.success("删除课程成功");
        } else {
            return ResultResponse.fail(403, "您不是管理员，无法创建课程");
        }
    }

}
