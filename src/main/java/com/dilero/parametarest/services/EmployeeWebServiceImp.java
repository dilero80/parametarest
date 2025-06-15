package com.dilero.parametarest.services;

import com.dilero.parametarest.entities.Employee;
import com.dilero.parametarest.repositories.EmployeeRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService
@Service
public class EmployeeWebServiceImp implements EmployeeWebService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @WebMethod
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);

    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(String id) {
        return employeeRepository.getByDocNumber(id);
    }

    public Employee deleteEmployee(String id) {
        return employeeRepository.deleteByDocNumber(id);
    }
}
