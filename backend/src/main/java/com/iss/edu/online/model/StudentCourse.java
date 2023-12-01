package com.iss.edu.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentCourse {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
}
