package com.company.microservices.controller;

import com.company.microservices.model.Driver;
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
    public ResponseEntity<String> saveDriver(@RequestBody Driver driver) {
        boolean result = driverService.saveDriver(driver);
        if(result){
            return ResponseEntity.ok("Driver Created Successfully!!!");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/driver")
    public ResponseEntity<List<Driver>> allDrivers() {
        List<Driver> drivers;
        drivers = driverService.allDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<Driver> findDriverById(@PathVariable("id") Long id) {
        Driver driver;
        driver = driverService.findById(id);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/driver/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable("id") Long id) {
        boolean result = driverService.deleteDriver(id);
        if(result){
            return ResponseEntity.ok("Driver deleted Successfully!!!");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
