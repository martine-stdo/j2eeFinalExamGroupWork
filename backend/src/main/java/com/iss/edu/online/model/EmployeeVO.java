package com.iss.edu.online.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeVO extends Employee implements Serializable {
    private String dname;
}
