package com.iss.edu.online.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TeacherDao {
    int deleteCourse(@Param("CourseID") Integer CourseID);
}
