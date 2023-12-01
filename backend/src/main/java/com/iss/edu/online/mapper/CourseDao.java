package com.iss.edu.online.mapper;

import com.iss.edu.online.model.CourseVO;
import com.iss.edu.online.model.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseDao {
    List<CourseVO> queryCourse(int start, int pageSize);

    int count();
}
