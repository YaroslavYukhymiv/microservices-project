package com.company.microservices.repository;

import com.company.microservices.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DriverDaoImpl implements DriverDao{

    private RedisTemplate redisTemplate;

    @Autowired
    public DriverDaoImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveDriver(Employee employee) {
        try {
            StringBuilder date = new StringBuilder(employee.getTime().toString());
            for (int i = 4; i < 14; i = i + 2) {
                date = date.deleteCharAt(i);
            }
            redisTemplate.opsForZSet().add(employee.getResource(), employee, (Double.parseDouble(date.toString())));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Set<Employee> findAllWayOfDriver(String resource) {
        Set<Employee> employees;
        employees = redisTemplate.opsForZSet().range(resource, 0, redisTemplate.opsForZSet().size(resource));
        return employees;
    }

    @Override
    public Employee lastPointOfDriver(String resource) {
        Set<Employee> employee = redisTemplate.opsForZSet().range(resource,
                (redisTemplate.opsForZSet().size(resource) - 1),
                 redisTemplate.opsForZSet().size(resource));

        Employee lastPointOfEmployee = null;
        for (Employee e : employee) {
             lastPointOfEmployee = e;
        }
        return lastPointOfEmployee;
    }
}
