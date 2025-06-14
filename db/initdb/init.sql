-- initdb/init.sql

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS employees;

USE employees;  --


CREATE TABLE IF NOT EXISTS employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    names VARCHAR(255) NULL UNIQUE,
    lastName VARCHAR(255) NULL,
    docType VARCHAR(255) NULL,
    docNumber int NULL,
    birthDate DATE NULL,
    vinculationDate DATE NULL,
    position VARCHAR(255) NULL,
    salary int NULL
    );




INSERT INTO employees (names, lastname,docType,docNumber,birthDate,vinculationDate,position,salary) VALUES ('Diego Leonardo', 'Romero Buitrago','CC',12345678,'1980-03-03','2023-12-28','Java Dev',10000000);
