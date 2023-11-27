package com.example.ehcache.spring.demo.person;

import java.util.List;

public interface PersonService {

    Person getById(Long id);
    List<Person> getAll();
}
