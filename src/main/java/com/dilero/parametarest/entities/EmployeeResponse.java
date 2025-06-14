package com.dilero.parametarest.entities;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Employee employee;
    private String bondingTime;
    private String age;

    public EmployeeResponse(Employee employee) {
        this.employee = employee;
    }

}
