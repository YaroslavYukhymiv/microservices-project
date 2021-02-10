package com.company.microservices.service;

import com.company.microservices.model.Employee;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface DriverService {

    void saveDriver(Employee employee);

    Set<Employee> allDrivers(String resource);

    Set<Employee> lastPointEmployee(String resource);

//    Employee findById(Long id);
//
//    boolean deleteDriver(Long id);

}
