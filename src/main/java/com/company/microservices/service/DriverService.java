package com.company.microservices.service;

import com.company.microservices.model.Employee;

import java.util.List;

public interface DriverService {

    boolean saveDriver(Employee employee);

    List<Employee> allDrivers();

    Employee findById(Long id);

    boolean deleteDriver(Long id);
}
