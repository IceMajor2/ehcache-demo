package com.example.ehcache.spring.demo.person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import javax.cache.Cache;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonCacheTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CacheManager cacheManager;

    private Cache<Long, Person> SUT; // personCache

    private static final String PERSON_CACHE = "personCache";

    @BeforeEach
    void setUp() {
        SUT = (Cache<Long, Person>) cacheManager.getCache(PERSON_CACHE).getNativeCache();
    }

    @AfterEach
    void tearDown() {
        cacheManager.getCache(PERSON_CACHE).clear();
    }

    @Test
    void shouldCachePersonOnGetById() {
        // arrange
        Person expectedPerson = new Person(1L, "John", "Kowalski", 44);
        Map.Entry<Long, Person> expected = Map.entry(1L, expectedPerson);

        // act
        personService.getById(1L);

        // assert
        Map<Long, Person> nestedMap = getCacheMap(SUT);
        assertThat(nestedMap).containsOnly(expected);
    }

    @Test
    void shouldCacheSeveralPeopleOnGetById() {
        // act
        personService.getById(1L);
        personService.getById(2L);
        personService.getById(3L);
        personService.getById(4L);
        personService.getById(5L);
        personService.getById(6L);

        // assert
        Map<Long, Person> cacheMap = getCacheMap(SUT);
        assertThat(cacheMap).size().isEqualTo(6);
    }

    private Map<Long, Person> getCacheMap(Cache<Long, Person> personCache) {
        return StreamSupport.stream(personCache.spliterator(), false)
                .collect(Collectors.toMap(Cache.Entry::getKey, Cache.Entry::getValue));
    }
}