package com.example.ehcache.spring.demo.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;

import javax.cache.Cache;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonServiceImplTest {

    @Autowired
    private CacheManager cacheManager;

    private Cache<SimpleKey, Map<Long, Person>> personCache = (Cache<SimpleKey, Map<Long, Person>>) cacheManager.getCache(PERSON_CACHE).getNativeCache();

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonRepository personRepository;

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
        Map<SimpleKey, Map<Long, Person>> cacheMap = getCacheAsMap(personCache);
        assertThat(cacheMap).containsOnly(expected);
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
        Map<SimpleKey, Map<Long, Person>> cacheMap = getCacheAsMap(personCache);
        assertThat(cacheMap).size().isEqualTo(6);
    }

    @Test
    void shouldCacheAllPeopleOnGetAll() {
        // act
        Iterable<Person> expected = personRepository.findAll();

        // assert
        Map<SimpleKey, Map<Long, Person>> cacheMap = getCacheAsMap(personCache);
        List<Person> actual = cacheMap.values().stream().toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCachePersonOnCachePerson() {
        // arrange
//        Person expectedPerson = new Person(1L, "John", "Kowalski", 44);
//        Map.Entry<Long, Person> expected = Map.entry(1L, expectedPerson);
//
//        // act
//        personService.cachePerson(personRepository.findById(1L).get());
//
//        // assert
//        Map<Long, Person> cacheMap = getCacheAsMap();
//        assertThat(cacheMap).containsOnly(expected);
    }

    private Map<SimpleKey, Map<Long, Person>> getCacheAsMap(Cache<SimpleKey, Map<Long, Person>> personCache) {
        Map<SimpleKey, Map<Long, Person>> cacheMap = StreamSupport.stream(personCache.spliterator(), false)
                .collect(Collectors.toMap(Cache.Entry::getKey, Cache.Entry::getValue));
        return cacheMap;
    }
}