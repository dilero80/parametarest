package com.dilero.parametarest.services;

import com.dilero.parametarest.entities.Employee;
import com.dilero.parametarest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeWebServiceImp implements EmployeeWebService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);

        return;


    }
}
