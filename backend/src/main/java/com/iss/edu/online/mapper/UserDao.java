package com.iss.edu.online.mapper;

import com.iss.edu.online.model.Course;
import com.iss.edu.online.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    User selectUser(@Param("username") String username);
    User selectById(@Param("id") Integer id);
    int insertUser(User user);

    int insertCourse(Course course);

    int deleteCourse(@Param("CourseID") Integer CourseID);
}
