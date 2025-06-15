package com.dilero.parametarest.repositories;

import com.dilero.parametarest.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Employee getByDocNumber(String docNumber);

    Employee deleteByDocNumber(String id);
}
