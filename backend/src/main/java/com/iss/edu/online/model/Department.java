package com.iss.edu.online.model;

import lombok.Data;

import java.util.List;

@Data
public class Department {

    private Integer deptno;

    private String dname;

    private String location;

    private List<Employee> employees;
}
