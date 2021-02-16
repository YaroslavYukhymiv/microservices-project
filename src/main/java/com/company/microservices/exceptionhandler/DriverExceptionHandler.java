package com.company.microservices.exceptionhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DriverExceptionHandler {

    Logger logger = LogManager.getLogger(DriverExceptionHandler.class);

    @ExceptionHandler(DriverException.class)
    public ResponseEntity<String> emptySetOfDriverException(DriverException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<String> redisConnectionFailureException() {
        logger.error("RedisConnectionFailureException, can not connect to the redis!!!");
        return new ResponseEntity("RedisConnectionFailureException, can not connect to the redis!!!", HttpStatus.BAD_REQUEST);
    }
}
