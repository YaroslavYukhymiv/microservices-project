package com.company.microservices.repository;

import com.company.microservices.model.Driver;

import java.util.List;

public interface DriverDao {
    boolean saveDriver(Driver driver);

    List<Driver> allDrivers();

    Driver findById(Long id);

    boolean deleteById(Long id);
}
