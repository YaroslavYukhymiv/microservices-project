package com.company.microservices.repository;

import com.company.microservices.model.Employee;

import java.util.Set;

public interface DriverDao {

    void saveDriver(Employee employee);

    Set<Employee> allDrivers(String resource);

    Set<Employee> lastPointEmployee(String resource);

//    Employee findById(Long id);
//
//    boolean deleteById(Long id);

}
