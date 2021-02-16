package com.company.microservices.repository;

import com.company.microservices.exceptionhandler.DriverException;
import com.company.microservices.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverDaoImplTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    RedisTemplate redisTemplate;

    @InjectMocks
    DriverDaoImpl driverDaoImpl;

    @Test
    public void saveDriverTest() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");
        employee.setLongitude(-115.171719);
        employee.setLatitude(36.121960);
        employee.setCompany("logos");
        employee.setTime(LocalDateTime.now());

        StringBuilder date = new StringBuilder(employee.getTime().toString());
        Point point = new Point(employee.getLongitude(), employee.getLatitude());

        for (int i = 4; i < 14; i = i + 2) {
            date = date.deleteCharAt(i);
        }
        when(redisTemplate.opsForZSet().add(employee.getResource(), employee, (Double.parseDouble(date.toString()))))
                .thenReturn(true);
        when(redisTemplate.opsForGeo().add("KEY", point, employee.getResource())).thenReturn(1L);
        driverDaoImpl.saveDriver(employee);
        verify(redisTemplate, times(2)).opsForZSet();
    }

    @Test(expected = DriverException.class)
    public void saveDriverWithSaveDriverErrorTest() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");
        employee.setLongitude(-115.171719);
        employee.setLatitude(36.121960);
        employee.setCompany("logos");
        employee.setTime(LocalDateTime.now());

        StringBuilder date = new StringBuilder(employee.getTime().toString());
        Point point = new Point(employee.getLongitude(), employee.getLatitude());

        for (int i = 4; i < 14; i = i + 2) {
            date = date.deleteCharAt(i);
        }
        when(redisTemplate.opsForGeo().add("KEY", point, employee.getResource())).thenReturn(1L);
        driverDaoImpl.saveDriver(employee);
    }

    @Test(expected = DriverException.class)
    public void saveDriverWithSaveDriverGeoErrorTest() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");
        employee.setLongitude(-115.171719);
        employee.setLatitude(36.121960);
        employee.setCompany("logos");
        employee.setTime(LocalDateTime.now());

        StringBuilder date = new StringBuilder(employee.getTime().toString());

        for (int i = 4; i < 14; i = i + 2) {
            date = date.deleteCharAt(i);
        }
        when(redisTemplate.opsForZSet().add(employee.getResource(), employee, (Double.parseDouble(date.toString()))))
                .thenReturn(false);
        driverDaoImpl.saveDriver(employee);
    }

    @Test(expected = DriverException.class)
    public void findAllWayOfDriverWithDriverTransactionExceptionTest() throws DriverException {
        when(redisTemplate.opsForZSet().range("resource", 0, redisTemplate.opsForZSet().size("resource"))).thenReturn(null);
        driverDaoImpl.findAllWayOfDriver("resource");
    }

    @Test(expected = DriverException.class)
    public void findAllWayOfDriverWithDriverNotFoundExceptionTest() throws DriverException {
        when(redisTemplate.opsForZSet().range("resource", 0, redisTemplate.opsForZSet().size("resource")).size()).thenReturn(0);
        driverDaoImpl.findAllWayOfDriver("resource");
    }

    @Test
    public void findAllWayOfDriver() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");

        Set<Employee> set = new HashSet<>();
        set.add(employee);

        when(redisTemplate.opsForZSet().range(employee.getResource(), 0, redisTemplate.opsForZSet().size(employee.getResource()))).thenReturn(set);
        Assert.assertEquals(set, driverDaoImpl.findAllWayOfDriver("Stepan"));
    }

    @Test
    public void lastPointOfDriverTest() throws DriverException {

        Employee employee = new Employee();
        employee.setResource("Stepan");

        Set<Employee> set = new HashSet<>();
        set.add(employee);

        when(redisTemplate.opsForZSet().range(employee.getResource(),
                (redisTemplate.opsForZSet().size(employee.getResource()) - 1),
                redisTemplate.opsForZSet().size(employee.getResource()))).thenReturn(set);

        Employee employee1 = driverDaoImpl.lastPointOfDriver("Stepan");

        Assert.assertEquals(employee, employee1);
    }

    @Test(expected = DriverException.class)
    public void lastPointOfDriverTestWithDriverTransactionExceptionTest() throws DriverException {
        when(redisTemplate.opsForZSet().range("resource",
                (redisTemplate.opsForZSet().size("resource") - 1),
                redisTemplate.opsForZSet().size("resource"))).thenReturn(null);
        driverDaoImpl.lastPointOfDriver("resource");
    }

    @Test(expected = DriverException.class)
    public void lastPointOfDriverTestWithDriverNotFoundExceptionTest() throws DriverException {
        when(redisTemplate.opsForZSet().range("resource",
                (redisTemplate.opsForZSet().size("resource") - 1),
                redisTemplate.opsForZSet().size("resource")).size()).thenReturn(0);
        driverDaoImpl.lastPointOfDriver("resource");
    }

    @Test(expected = DriverException.class)
    public void radiusWithExceptionTest() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");
        double radius = 100d;
        Point point = new Point(employee.getLongitude(), employee.getLatitude());
        Circle circle = new Circle(point, radius);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands
                .GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeCoordinates()
                .includeDistance();
        when(redisTemplate.opsForGeo().radius("KEY", circle,  args)).thenReturn(null);
        driverDaoImpl.radius("Stepan", 100);
    }
}