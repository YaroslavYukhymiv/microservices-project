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
            String data = employee.getTime().toString();
            String[] splitData = data.split("T");
            String[] splitDataOne = splitData[0].split("-");
            String[] splitDataTwo = splitData[1].split(":");
            String result = new String();

            for (String s : splitDataOne) {
                result = result + s;
            }
            for (String s : splitDataTwo) {
                result = result + s;
            }
            Double score = Double.parseDouble(result);

            redisTemplate.opsForZSet().add(employee.getResource(), employee, score);
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
