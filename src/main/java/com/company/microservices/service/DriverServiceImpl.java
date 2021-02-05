package com.company.microservices.service;

import com.company.microservices.model.Employee;
import com.company.microservices.repository.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverDao driverDao;

    @Override
    public boolean saveDriver(Employee employee) {
        return driverDao.saveDriver(employee);
    }

    @Override
    public List<Employee> allDrivers() {
        return driverDao.allDrivers();
    }

    @Override
    public Employee findById(Long id) {
        return driverDao.findById(id);
    }

    @Override
    public boolean deleteDriver(Long id) {
        return driverDao.deleteById(id);
    }

    @Override
    public Employee findById(String resource) {
        return driverDao.findByResource(resource);
    }

    @Override
    public boolean saveEmployee(Employee employee) {
        return driverDao.saveEmployee(employee);
    }

    @Override
    public Set<Employee> lastPointOfResource(String resource) {
        return driverDao.lastPointOfResource(resource);
    }

}
