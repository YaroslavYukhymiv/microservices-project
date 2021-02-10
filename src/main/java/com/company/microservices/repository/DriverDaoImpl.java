package com.company.microservices.repository;

import com.company.microservices.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    @Override
    public Employee findByResource(String resource) {
        Employee employee;
        employee = (Employee) redisTemplate.opsForHash().get(KEY, resource);
        return employee;
    }

    @Override
    public boolean saveEmployee(Employee employee) {

        try {
            StringBuilder sb = new StringBuilder(employee.getTime().toString());
            sb.deleteCharAt(4);
            sb.deleteCharAt(7);
            sb.deleteCharAt(10);
            sb.deleteCharAt(13);
            sb.deleteCharAt(16);

            redisTemplate.opsForZSet().add(employee.getResource(), employee, Double.parseDouble(sb.toString()));
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<Employee> allPointsOfResource(String resource) {
       Set<Employee> employees = redisTemplate.opsForZSet().range(resource, 0, redisTemplate.opsForZSet().size(resource));
       return employees;
    }

    @Override
    public Set<Employee> lastPointOfResource(String resource) {
        Set<Employee> employees = redisTemplate.opsForZSet().range(resource, redisTemplate.opsForZSet().size(resource) - 1,
                redisTemplate.opsForZSet().size(resource));
        return employees;
    }
}
