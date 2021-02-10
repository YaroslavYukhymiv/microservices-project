package com.company.microservices.service;

import com.company.microservices.model.Employee;

import java.util.List;
import java.util.Set;

public interface DriverService {

    boolean saveDriver(Employee employee);

    List<Employee> allDrivers();

    Employee findById(Long id);

    boolean deleteDriver(Long id);

    Employee findById(String resource);

    Set<Employee> lastPointOfResource(String resource);

    boolean saveEmployee(Employee employee);
}
