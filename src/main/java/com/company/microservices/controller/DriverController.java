package com.company.microservices.controller;

import com.company.microservices.model.Employee;
import com.company.microservices.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/driver")
    public ResponseEntity<String> saveDriver(@RequestBody Employee employee) {
        boolean result = driverService.saveDriver(employee);
        if(result){
            return ResponseEntity.ok("Employee Created Successfully!!!");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/driver")
    public ResponseEntity<List<Employee>> allDrivers() {
        List<Employee> employees;
        employees = driverService.allDrivers();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<Employee> findDriverById(@PathVariable("id") Long id) {
        Employee employee;
        employee = driverService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/driver/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable("id") Long id) {
        boolean result = driverService.deleteDriver(id);
        if(result){
            return ResponseEntity.ok("Employee deleted Successfully!!!");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getResource")
    public ResponseEntity<Employee> gerResource(String resource) {
        Employee employee;
        employee = driverService.findById(resource);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employee")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
        boolean result = driverService.saveEmployee(employee);
        if(result){
            return ResponseEntity.ok("Employee Created Successfully!!!");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> allEmployee() {
        List<Employee> employees;
        employees = driverService.allDrivers();
        return ResponseEntity.ok(employees);
    }



}
