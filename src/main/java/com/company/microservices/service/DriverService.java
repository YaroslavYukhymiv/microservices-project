package com.company.microservices.service;

import com.company.microservices.model.Driver;

import java.util.List;

public interface DriverService {

    boolean saveDriver(Driver driver);

    List<Driver> allDrivers();

    Driver findById(Long id);

    boolean deleteDriver(Long id);
}
