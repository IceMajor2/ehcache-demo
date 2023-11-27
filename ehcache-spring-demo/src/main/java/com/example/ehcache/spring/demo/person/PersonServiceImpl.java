package com.example.ehcache.spring.demo.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Cacheable(
            value = "personCache",
            key = "#id"
    )
    public Person getById(Long id) {
        Optional<Person> optPerson = personRepository.findById(id);
        if (optPerson.isEmpty()) {
            return null;
        }
        Person person = optPerson.get();
        log.info("Query 'getById' in PersonService returned [{}]", person);
        return person;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }
}
