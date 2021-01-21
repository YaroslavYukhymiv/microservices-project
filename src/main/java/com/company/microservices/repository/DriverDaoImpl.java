package com.company.microservices.repository;

import com.company.microservices.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverDaoImpl implements DriverDao{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "DRIVER";

    @Override
    public boolean saveDriver(Employee employee) {
        try {
            redisTemplate.opsForHash().put(KEY, employee.getId().toString(), employee);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Employee> allDrivers() {
        List<Employee> employees;
        employees = redisTemplate.opsForHash().values(KEY);
        return employees;
    }

    @Override
    public Employee findById(Long id) {
        Employee employee;
        employee = (Employee) redisTemplate.opsForHash().get(KEY, id.toString());
        return employee;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            redisTemplate.opsForHash().delete(KEY, id.toString());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
