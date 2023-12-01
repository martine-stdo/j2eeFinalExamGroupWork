package com.iss.edu.online.controller.CourseController;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.CourseDao;
import com.iss.edu.online.model.Course;
import com.iss.edu.online.model.CourseVO;
import com.iss.edu.online.model.Employee;
import com.iss.edu.online.model.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ListOfCourseInfo {

    @Autowired
    private CourseDao courseDao;

    // POST 请求，接收分页请求参数 PageRequest
    @PostMapping("/queryCourse")
    public ResultResponse queryCourses(@RequestBody PageRequest pageRequest) {
        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 检查参数是否为空
        if (pageNum == null || pageSize == null) {
            return ResultResponse.fail(-1, "参数错误!");
        }

        // 计算起始位置
        int start = (pageNum - 1) * pageSize;

        // 查询课程信息列表
        List<CourseVO> courses = courseDao.queryCourse(start, pageSize);

        // 查询课程总数
        int total = courseDao.count();

        // 计算总页数
        int totalPage = (int) Math.ceil(total * 1.0 / pageSize);

        // 构建返回数据的 Map
        Map<String, Object> data = new HashMap<>();
        data.put("totalPage", totalPage);
        data.put("courses", courses);

        // 返回成功响应，并附带数据
        return ResultResponse.success(data);
    }
}
