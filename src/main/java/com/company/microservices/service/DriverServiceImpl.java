package com.company.microservices.service;

import com.company.microservices.exceptionhandler.DriverException;
import com.company.microservices.model.Employee;
import com.company.microservices.repository.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DriverServiceImpl implements DriverService{

    private DriverDao driverDao;

    @Autowired
    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public void saveDriver(Employee employee) throws DriverException {
        driverDao.saveDriver(employee);
    }

    @Override
    public Set<Employee> findAllWayOfDriver(String resource) throws DriverException {
        return driverDao.findAllWayOfDriver(resource);
    }

    @Override
    public Employee lastPointOfDriver(String resource) throws DriverException {
        return driverDao.lastPointOfDriver(resource);
    }

    @Override
    public GeoResults radius(String resource, double radius) throws DriverException {
        return driverDao.radius(resource, radius);
    }
}
