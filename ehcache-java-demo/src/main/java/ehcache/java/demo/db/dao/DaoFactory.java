package ehcache.java.demo.db.dao;

import ehcache.java.demo.person.Person;
import ehcache.java.demo.person.cache.PersonCache;
import ehcache.java.demo.person.dao.PersonDaoImpl;

import java.sql.Connection;

public class DaoFactory {

    public static Dao<Person> createPersonDao(Connection connection, PersonCache personCache) {
        return new PersonDaoImpl(connection, personCache);
    }
}
