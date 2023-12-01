package com.iss.edu.online.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseVO extends Course implements Serializable {
    private String username;
}
