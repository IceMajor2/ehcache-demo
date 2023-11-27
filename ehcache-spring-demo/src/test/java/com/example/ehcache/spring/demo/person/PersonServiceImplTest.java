package com.example.ehcache.spring.demo.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonServiceImplTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PersonServiceImpl personService;

    private static final String PERSON_CACHE = "personCache";

    @BeforeEach
    void setUp() {
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
        Map<Long, Person> cacheMap = getCacheAsMap();
        assertThat(cacheMap).containsOnly(expected);
    }

    private Map<Long, Person> getCacheAsMap() {
        Cache<Long, Person> personCache = cacheManager.getCache(PERSON_CACHE);
        Map<Long, Person> cacheMap = StreamSupport.stream(personCache.spliterator(), false)
                .collect(Collectors.toMap(Cache.Entry::getKey, Cache.Entry::getValue));
        return cacheMap;
    }
}