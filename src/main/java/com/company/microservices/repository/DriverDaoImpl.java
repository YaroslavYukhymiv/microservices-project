package com.company.microservices.repository;

import com.company.microservices.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DriverDaoImpl implements DriverDao{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveDriver(Employee employee) {
        try {
            StringBuilder date = new StringBuilder(employee.getTime().toString());
            for (int i = 4; i < 14; i = i + 2) {
                date = date.deleteCharAt(i);
            }

            Double score = Double.parseDouble(date.toString());

            redisTemplate.opsForZSet().add(employee.getResource(), employee, score);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Set<Employee> allDrivers(String resource) {
        Set<Employee> employees;
        employees = redisTemplate.opsForZSet().range(resource, 0, redisTemplate.opsForZSet().size(resource));
        return employees;
    }

    @Override
    public Set<Employee> lastPointEmployee(String resource) {
        Set<Employee> employees;
        employees = redisTemplate.opsForZSet().range(resource, (redisTemplate.opsForZSet().size(resource) - 1), redisTemplate.opsForZSet().size(resource));
        return employees;
    }

//    @Override
//    public Employee findById(Long id) {
//        Employee employee;
//        employee = (Employee) redisTemplate.opsForHash().get(KEY, id.toString());
//        return employee;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        try {
//            redisTemplate.opsForHash().delete(KEY, id.toString());
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }

}
