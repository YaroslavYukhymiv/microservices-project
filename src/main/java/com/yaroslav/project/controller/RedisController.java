package com.yaroslav.project.controller;

import com.yaroslav.project.model.PersonLocation;
import com.yaroslav.project.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    RedisRepository redisRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void radisSave(String firstName){

        System.out.println("work");
        redisRepository.save(new PersonLocation("Andriy"));


    }
}
