package com.company.microservices.repository;

import com.company.microservices.model.Employee;

import java.util.List;
import java.util.Set;

public interface DriverDao {

    boolean saveDriver(Employee employee);

    List<Employee> allDrivers();

    Employee findById(Long id);

    boolean deleteById(Long id);

    Employee findByResource(String resource);

    boolean saveEmployee(Employee employee);

    Set<Employee> allPointsOfResource(String resource);

    Set<Employee> lastPointOfResource (String resource);
}
