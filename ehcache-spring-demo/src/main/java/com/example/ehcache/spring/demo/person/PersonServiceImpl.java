package com.example.ehcache.spring.demo.person;

import com.example.ehcache.spring.demo.util.CollectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Cacheable(value = "personCache")
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
    @Cacheable("personCache")
    public Map<Long, Person> getAll() {
        Map<Long, Person> people = personRepository.findAll()
                .stream()
                .collect(CollectionUtils.toLinkedMap(Person::id, Function.identity()));
        log.info("Query 'getAll' in PersonService returned {} rows", people.size());
        return people;
    }
}
