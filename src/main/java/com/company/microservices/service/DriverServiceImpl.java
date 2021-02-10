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

    @Autowired
    private DriverDao driverDao;

    @Override
    public void saveDriver(Employee employee) {
        driverDao.saveDriver(employee);
    }

    @Override
    public Set<Employee> allDrivers(String resource) {
        return driverDao.allDrivers(resource);
    }

    @Override
    public Set<Employee> lastPointEmployee(String resource) {
        return driverDao.lastPointEmployee(resource);
    }

//    @Override
//    public Employee findById(Long id) {
//        return driverDao.findById(id);
//    }
//
//    @Override
//    public boolean deleteDriver(Long id) {
//        return driverDao.deleteById(id);
//    }

}
