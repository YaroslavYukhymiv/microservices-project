package com.company.microservices.repository;

import com.company.microservices.model.Driver;
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
    public boolean saveDriver(Driver driver) {
        try {
            redisTemplate.opsForHash().put(KEY, driver.getId().toString(), driver);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Driver> allDrivers() {
        List<Driver> drivers;
        drivers = redisTemplate.opsForHash().values(KEY);
        return drivers;
    }

    @Override
    public Driver findById(Long id) {
        Driver driver;
        driver = (Driver) redisTemplate.opsForHash().get(KEY, id.toString());
        return driver;
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
