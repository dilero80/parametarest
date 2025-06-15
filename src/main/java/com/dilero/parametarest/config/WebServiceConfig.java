package com.dilero.parametarest.config;

import com.dilero.parametarest.services.EmployeeWebService;
import com.dilero.parametarest.services.EmployeeWebServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;



@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public MessageDispatcherServlet messageDispatcherServlet() {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        return servlet;
    }

    @Bean
    public String messageDispatcherServletRegistration() {
        return messageDispatcherServlet().toString();
    }

    @Bean
    public EmployeeWebService employeeWebService() {
        return new EmployeeWebServiceImp();
    }
}