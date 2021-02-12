package com.company.microservices.controller;

import com.company.microservices.model.Employee;
import com.company.microservices.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PutMapping("/driver")
    public ResponseEntity<String> saveDriver(@RequestBody Employee employee) {
        driverService.saveDriver(employee);
        return ResponseEntity.ok("Employee Created Successfully!!!");
    }

    @GetMapping ("/findAllWayOfDriver")
    public ResponseEntity<Set<Employee>> findAllWayOfDriver(@RequestParam String resource) {
        return ResponseEntity.ok(driverService.findAllWayOfDriver(resource));
    }


    @GetMapping("/lastPointOfDriver")
    public ResponseEntity<Employee> lastPointOfDriver(@RequestParam String resource) {
        return ResponseEntity.ok(driverService.lastPointOfDriver(resource));
    }
}
