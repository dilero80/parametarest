package com.dilero.parametarest.services;

import com.dilero.parametarest.entities.Employee;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface EmployeeWebService {

    @WebMethod
    void saveEmployee(Employee employee);
}
