package com.voysovych.finalproject.service;

import com.voysovych.finalproject.model.Driver;

import java.util.List;

public interface DriverService {

    boolean saveDriver(Driver driver);

    List<Driver> allDrivers();

    Driver findById(Long id);

    boolean deleteDriver(Long id);
}
