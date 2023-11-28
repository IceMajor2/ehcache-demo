package com.example.ehcache.spring.demo.person;

import java.util.Map;

public interface PersonService {

    Person getById(Long id);
    Map<Long, Person> getAll();
}
