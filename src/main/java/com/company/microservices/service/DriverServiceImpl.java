package com.company.microservices.service;

import com.company.microservices.model.Driver;
import com.company.microservices.repository.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverDao driverDao;

    @Override
    public boolean saveDriver(Driver driver) {
        return driverDao.saveDriver(driver);
    }

    @Override
    public List<Driver> allDrivers() {
        return driverDao.allDrivers();
    }

    @Override
    public Driver findById(Long id) {
        return driverDao.findById(id);
    }

    @Override
    public boolean deleteDriver(Long id) {
        return driverDao.deleteById(id);
    }
}
