package com.company.microservices.service;

import com.company.microservices.model.Employee;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface DriverService {

    void saveDriver(Employee employee);

    Set<Employee> findAllWayOfDriver(String resource);

    Employee lastPointOfDriver(String resource);
}
