package com.dilero.parametarest.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name= "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String lastName;
    private String docType;
    private String docNumber;
    private Date birthDate;
    private Date bondingDate;
    private String position;
    private Double salary;


}
