package com.voysovych.finalproject.repository;

import com.voysovych.finalproject.model.Driver;

import java.util.List;

public interface DriverDao {
    boolean saveDriver(Driver driver);

    List<Driver> allDrivers();

    Driver findById(Long id);

    boolean deleteById(Long id);
}
