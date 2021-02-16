package com.company.microservices.config;

import com.company.microservices.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${redisHostName}")
    private String hostName;

    @Value("${redisPort}")
    private int redisPort;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(redisPort);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        return jedisConnectionFactory;
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Employee> redisTemplate() {
        final RedisTemplate<String, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        Jackson2JsonRedisSerializer<Employee> values = new Jackson2JsonRedisSerializer<>(Employee.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        values.setObjectMapper(objectMapper);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(values);
        return template;
    }

    @Bean("redisGeoTemplate")
    public RedisTemplate<String, String> redisGeoTemplate() {
        final RedisTemplate<String, String> redisGeoTemplate = new RedisTemplate<>();
        redisGeoTemplate.setConnectionFactory(jedisConnectionFactory());
        redisGeoTemplate.setKeySerializer(new StringRedisSerializer());
        redisGeoTemplate.setValueSerializer(new StringRedisSerializer());
        redisGeoTemplate.setDefaultSerializer(new StringRedisSerializer());
        return redisGeoTemplate;
    }
}
