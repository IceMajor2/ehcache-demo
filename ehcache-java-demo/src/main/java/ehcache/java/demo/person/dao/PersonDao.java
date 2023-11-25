package ehcache.java.demo.person.dao;

import ehcache.java.demo.person.Person;

import java.util.List;

public interface PersonDao {

    Person findById(Long id);
    List<Person> findAll();
    void save(Person person);
}
