package com.iss.edu.online.model;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {

    private Integer empno;

    private String ename;

    private String password;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private Double sal;

    private Double comm;

    private Department department;

}
