package ehcache.java.demo.db.dao;

import ehcache.java.demo.person.Person;
import ehcache.java.demo.person.dao.PersonDaoImpl;

import java.sql.Connection;

public class DaoFactory {

    public static Dao<Person> createPersonDao(Connection connection) {
        return new PersonDaoImpl(connection);
    }
}
