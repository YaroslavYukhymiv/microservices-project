package com.company.microservices.service;

import com.company.microservices.model.Employee;
import com.company.microservices.repository.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DriverServiceImpl implements DriverService{

    private DriverDao driverDao;

    @Autowired
    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public void saveDriver(Employee employee) {
        driverDao.saveDriver(employee);
    }

    @Override
    public Set<Employee> findAllWayOfDriver(String resource) {
        return driverDao.findAllWayOfDriver(resource);
    }

    @Override
    public Employee lastPointOfDriver(String resource) {
        return driverDao.lastPointOfDriver(resource);
    }
}
