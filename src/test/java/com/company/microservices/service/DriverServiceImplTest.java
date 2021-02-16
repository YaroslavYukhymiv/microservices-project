package com.company.microservices.service;

import com.company.microservices.exceptionhandler.DriverException;
import com.company.microservices.model.Employee;
import com.company.microservices.repository.DriverDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverServiceImplTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    DriverDao driverDao;

    @InjectMocks
    DriverServiceImpl driverService;

    @Test
    public void saveDriverTest() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");
        driverService.saveDriver(employee);
        verify(driverDao, times(1)).saveDriver(employee);
    }

    @Test
    public void findAllWayOfDriverTest() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");

        Set<Employee> set = new HashSet<>();
        set.add(employee);

        when(driverDao.findAllWayOfDriver("Stepan")).thenReturn(set);
        Assert.assertEquals(set, driverService.findAllWayOfDriver("Stepan"));
        verify(driverDao, times(1)).findAllWayOfDriver("Stepan");
    }

    @Test
    public void lastPointOfDriverTest() throws DriverException {
        Employee employee = new Employee();
        employee.setResource("Stepan");

        when(driverDao.lastPointOfDriver("Stepan")).thenReturn(employee);
        Assert.assertEquals(employee, driverService.lastPointOfDriver("Stepan"));
        verify(driverDao, times(1)).lastPointOfDriver("Stepan");
    }

    @Test
    public void radiusTest() throws DriverException {
        Distance distanceOne = new Distance(0.1013);
        Distance distanceTwo = new Distance(22.2928);

        GeoResult geoResultOne = new GeoResult("RedisGeoCommands.GeoLocation(" +
                "name=Ivan, point=Point [x=-115.171719, y=36.121960])", distanceOne);
        GeoResult geoResultTwo = new GeoResult("RedisGeoCommands.GeoLocation(" +
                "name=Stepan, point=Point [x=-115.171719, y=36.122160])", distanceTwo);

        List list = new ArrayList();
        list.add(geoResultOne);
        list.add(geoResultTwo);

        GeoResults geoResults = new GeoResults(list);

        when(driverDao.radius("Stepan", 400)).thenReturn(geoResults);
        driverService.radius("Stepan", 400);
        verify(driverDao, times(1)).radius("Stepan", 400);
    }
}