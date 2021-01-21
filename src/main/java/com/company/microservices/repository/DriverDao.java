package com.company.microservices.repository;

import com.company.microservices.model.Employee;

import java.util.List;

public interface DriverDao {

    boolean saveDriver(Employee employee);

    List<Employee> allDrivers();

    Employee findById(Long id);

    boolean deleteById(Long id);
}
