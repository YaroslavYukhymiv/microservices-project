package com.company.microservices.service;

import com.company.microservices.exceptionhandler.DriverException;
import com.company.microservices.model.Employee;
import org.springframework.data.geo.GeoResults;

import java.util.Set;

public interface DriverService {

    void saveDriver(Employee employee) throws DriverException;

    Set<Employee> findAllWayOfDriver(String resource) throws DriverException;

    Employee lastPointOfDriver(String resource) throws DriverException;

    GeoResults radius(String resource, double radius) throws DriverException;
}
