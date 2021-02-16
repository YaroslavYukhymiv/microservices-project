package com.company.microservices.repository;

import com.company.microservices.exceptionhandler.DriverException;
import com.company.microservices.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public class DriverDaoImpl implements DriverDao{

    private final RedisTemplate redisTemplate;
    private final RedisTemplate redisGeoTemplate;

    private static final String KEY = "LOCATION";

    Logger logger = LogManager.getLogger(DriverDaoImpl.class);

    @Autowired
    public DriverDaoImpl(@Qualifier("redisTemplate") RedisTemplate redisTemplate,
                         @Qualifier("redisGeoTemplate") RedisTemplate redisGeoTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisGeoTemplate =redisGeoTemplate;
    }

    @Override
    public void saveDriver(Employee employee) throws DriverException {
        StringBuilder date = new StringBuilder(employee.getTime().toString());
        Point point = new Point(employee.getLongitude(), employee.getLatitude());

        for (int i = 4; i < 14; i = i + 2) {
            date = date.deleteCharAt(i);
        }

        if(!redisTemplate.opsForZSet().add(employee.getResource(), employee, (Double.parseDouble(date.toString())))) {
            throw new DriverException("DriverException, Driver haven't saved!!!");
        }
        if(redisGeoTemplate.opsForGeo().add(KEY, point, employee.getResource()) == null) {
            throw new DriverException("DriverException, Driver geos haven't saved!!!");
        }
    }

    @Override
    public Set<Employee> findAllWayOfDriver(String resource) throws DriverException {

        if (redisTemplate.opsForZSet().range(resource, 0, redisTemplate.opsForZSet().size(resource)) == null)
        {
            logger.warn("DriverException,used in pipeline / transaction!!!");
            throw new DriverException("DriverException, used in pipeline / transaction!!!");
        }
        if (redisTemplate.opsForZSet().range(resource, 0, redisTemplate.opsForZSet().size(resource)).size() == 0) {
            logger.warn("DriverException, returned empty set, maybe resource doesn't exist!!!");
            throw new DriverException("DriverException, returned empty set, maybe resource doesn't exist!!!");
        }

        return redisTemplate.opsForZSet().range(resource, 0, redisTemplate.opsForZSet().size(resource));
    }

    @Override
    public Employee lastPointOfDriver(String resource) throws DriverException {
        if (redisTemplate.opsForZSet().range(resource,
                (redisTemplate.opsForZSet().size(resource) - 1),
                redisTemplate.opsForZSet().size(resource)) == null) {
            logger.warn("DriverException, used in pipeline / transaction!!!");
            throw new DriverException("DriverException, used in pipeline / transaction!!!");
        }
        if (redisTemplate.opsForZSet().range(resource,
                (redisTemplate.opsForZSet().size(resource) - 1),
                redisTemplate.opsForZSet().size(resource)).size()  == 0) {
            logger.warn("DriverException, returned empty set, maybe resource doesn't exist!!!");
            throw new DriverException("DriverException, returned empty set, maybe resource doesn't exist!!!");
        }

        Set<Employee> employee = redisTemplate.opsForZSet().range(resource,
                (redisTemplate.opsForZSet().size(resource) - 1),
                redisTemplate.opsForZSet().size(resource));

        Employee lastPointOfEmployee = null;
        for (Employee e : employee) {
             lastPointOfEmployee = e;
        }
        return lastPointOfEmployee;
    }

    @Override
    public GeoResults radius(String resource, double radius) throws DriverException {
        Employee employee = lastPointOfDriver(resource);
        Point point = new Point(employee.getLongitude(), employee.getLatitude());
        Circle circle = new Circle(point, radius);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands
                .GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeCoordinates()
                .includeDistance();
        if (redisGeoTemplate.opsForGeo().radius(KEY, circle,  args) == null) {
            logger.warn("DriverException, method radius used in pipeline / transaction!!!");
            throw new DriverException("DriverException, method radius used in pipeline / transaction!!!");
        }
        return redisGeoTemplate.opsForGeo().radius(KEY, circle,  args);
    }
}
