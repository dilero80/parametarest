package com.dilero.parametarest;

import com.dilero.parametarest.services.EmployeeWebServiceImp;


import jakarta.xml.ws.Endpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParametarestApplication {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8082/empleadoService", new EmployeeWebServiceImp());
		System.out.println("Servicio SOAP publicado en http://localhost:8080/empleadoService");
		SpringApplication.run(ParametarestApplication.class, args);

	}

}
