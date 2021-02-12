package com.company.microservices.repository;

import com.company.microservices.model.Employee;

import java.util.Set;

public interface DriverDao {

    void saveDriver(Employee employee);

    Set<Employee> findAllWayOfDriver(String resource);

    Employee lastPointOfDriver(String resource);
}
