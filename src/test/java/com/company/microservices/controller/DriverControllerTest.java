package com.company.microservices.controller;

import com.company.microservices.exceptionhandler.DriverException;
import com.company.microservices.model.Employee;
import com.company.microservices.service.DriverService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(DriverController.class)
public class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    @InjectMocks
    private DriverController driverController;

    @Test
    public void findAllWayOfDriverTest() throws Exception, DriverException {

        Employee employee = new Employee();
        employee.setResource("Stepan");

        Set<Employee> set = new HashSet<>();
        set.add(employee);

        given(driverService.findAllWayOfDriver("Stepan")).willReturn(set);

        this.mockMvc.perform(get("/findAllWayOfDriver").param("resource","Stepan")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].resource", is(employee.getResource())));

        verify(driverService, times(1)).findAllWayOfDriver("Stepan");
    }

    @Test
    public void lastPointOfDriverTest() throws Exception, DriverException {

        Employee employee = new Employee();
        employee.setResource("Stepan");

        given(driverService.lastPointOfDriver("Stepan")).willReturn(employee);

        this.mockMvc.perform(get("/lastPointOfDriver").param("resource","Stepan")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resource", is(employee.getResource())));

        verify(driverService, times(1)).lastPointOfDriver("Stepan");
    }

    @Test
    public void radiusTest() throws DriverException, Exception {

        Distance distanceOne = new Distance(0.1013);
        Distance distanceTwo = new Distance(22.2928);

        GeoResult geoResultOne = new GeoResult("RedisGeoCommands.GeoLocation(name=Ivan, point=Point [x=-115.171719, y=36.121960])", distanceOne);
        GeoResult geoResultTwo = new GeoResult("RedisGeoCommands.GeoLocation(name=Stepan, point=Point [x=-115.171719, y=36.122160])", distanceTwo);

        List list = new ArrayList();
        list.add(geoResultOne);
        list.add(geoResultTwo);

        GeoResults geoResults = new GeoResults(list);

        given(driverService.radius("Stepan", 300d)).willReturn(geoResults);

        this.mockMvc.perform(get("/radius")
                .param("resource","Stepan")
                .param("radius", "300")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(driverService, times(1)).radius("Stepan", 300);


    }
}