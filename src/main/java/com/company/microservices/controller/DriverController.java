package com.company.microservices.controller;

import com.company.microservices.model.Employee;
import com.company.microservices.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/driver")
    public ResponseEntity<String> saveDriver(@RequestBody Employee employee) {
        driverService.saveDriver(employee);
        return ResponseEntity.ok("Employee Created Successfully!!!");
    }

    @GetMapping ("/findDriver")
    public ResponseEntity<Set<Employee>> allDrivers(@RequestParam String resource) {
        Set<Employee> employees;
        employees = driverService.allDrivers(resource);
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/lastPoint")
    public ResponseEntity<Employee> lastPoint(@RequestParam String resource) {
        Employee employee = new Employee();
        Set<Employee> employees;
        employees = driverService.lastPointEmployee(resource);
        for (Employee e : employees) {
            employee = e;
        }
        return ResponseEntity.ok(employee);
    }

//    @GetMapping("/driver/{id}")
//    public ResponseEntity<Employee> findDriverById(@PathVariable("id") Long id) {
//        Employee employee;
//        employee = driverService.findById(id);
//        return ResponseEntity.ok(employee);
//    }
//
//    @DeleteMapping("/driver/{id}")
//    public ResponseEntity<String> deleteDriver(@PathVariable("id") Long id) {
//        boolean result = driverService.deleteDriver(id);
//        if(result){
//            return ResponseEntity.ok("Employee deleted Successfully!!!");
//        }else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
}
