package com.dilero.parametarest.controllers;


import com.dilero.parametarest.entities.Employee;
import com.dilero.parametarest.entities.EmployeeResponse;
import com.dilero.parametarest.services.EmployeeWebServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("employee")
public class employeeController {

    @Autowired
    EmployeeWebServiceImp employeeWebServiceImp;

    @GetMapping
    public ResponseEntity<EmployeeResponse> crearEmpleado(
            @RequestParam String names,
            @RequestParam String lastNames,
            @RequestParam String idType,
            @RequestParam String idNumber,
            @RequestParam String birthDate,
            @RequestParam String bondingDate,
            @RequestParam String position,
            @RequestParam Double salary) {

        // Validations
        if (names.isEmpty() || lastNames.isEmpty() || idType.isEmpty() ||
                idNumber.isEmpty() || birthDate.isEmpty() ||
                bondingDate.isEmpty() || position.isEmpty() || salary == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Date birthD;
        Date vincDate;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthD = sdf.parse(birthDate);
            vincDate = sdf.parse(bondingDate);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Age verification
        if (!isAdult(birthD)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        Employee employee = new Employee();
        employee.setNames(names);
        employee.setLastName(lastNames);
        employee.setDocType(idType);
        employee.setDocNumber(idNumber);
        employee.setBirthDate(birthD);
        employee.setBondingDate(vincDate);
        employee.setPosition(position);
        employee.setSalary(salary);

        System.out.println(employee);
        // Call SOAP Service
        employeeWebServiceImp.saveEmployee(employee);

        // Calculate bonding time and age
        EmployeeResponse response = new EmployeeResponse(employee);
        response.setBondingTime(calculateBondingTime(vincDate));
        response.setAge(calculateAge(birthD));



        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable String id){
        // Validations
        if (id.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Employee employee=employeeWebServiceImp.getEmployee(id);
        EmployeeResponse response = new EmployeeResponse(employee);
        response.setBondingTime(calculateBondingTime(employee.getBondingDate()));
        response.setAge(calculateAge(employee.getBirthDate()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable String id){
        if (id.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Employee employee=employeeWebServiceImp.deleteEmployee(id);
        EmployeeResponse response = new EmployeeResponse(employee);
        response.setBondingTime(calculateBondingTime(employee.getBondingDate()));
        response.setAge(calculateAge(employee.getBirthDate()));
        return ResponseEntity.ok(response);

    }

    private boolean isAdult(Date birthDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        int bornYear = calendar.get(Calendar.YEAR);
        int age = Calendar.getInstance().get(Calendar.YEAR) - bornYear;
        return age >= 18;
    }

    private String calculateBondingTime(Date bondingDate) {
        LocalDate today = LocalDate.now();
        Instant bondingDateIns = bondingDate.toInstant();
        LocalDate bondingFinalDate = bondingDateIns.atZone(ZoneId.systemDefault()).toLocalDate();;
        long yearsDifference = ChronoUnit.YEARS.between(bondingFinalDate,today);
        long monthsDifference = ChronoUnit.MONTHS.between(bondingFinalDate,today) % 12;
        long daysDifference = ChronoUnit.DAYS.between(bondingFinalDate.plusYears(yearsDifference).plusMonths(monthsDifference),today);

        return "Years "+ yearsDifference + " Months "+monthsDifference +" days: "+ daysDifference; // Placeholder
    }

    private String calculateAge(Date birthDate) {
        LocalDate today = LocalDate.now();
        Instant birthDateIns = birthDate.toInstant();
        LocalDate bondingFinalDate = birthDateIns.atZone(ZoneId.systemDefault()).toLocalDate();;
        long yearsDifference = ChronoUnit.YEARS.between(bondingFinalDate,today);
        return "Years "+ yearsDifference ;
    }

}
