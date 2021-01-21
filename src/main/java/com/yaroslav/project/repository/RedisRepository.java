package com.yaroslav.project.repository;

import com.yaroslav.project.model.PersonLocation;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends KeyValueRepository<PersonLocation, String> {

    public PersonLocation findPersonLocationByFirstName(String firstName);

}
