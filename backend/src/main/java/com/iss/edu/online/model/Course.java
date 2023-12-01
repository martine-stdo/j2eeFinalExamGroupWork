package com.iss.edu.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    private Integer courseId;
    private String courseName;
    private String courseDescription;
    private LocalDateTime courseStartTime;
    private LocalDateTime courseEndTime;
    private Integer teacherId;

}

