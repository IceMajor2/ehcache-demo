package ehcache.java.demo.person.cache;

import ehcache.java.demo.db.InMemoryDatabase;
import ehcache.java.demo.db.dao.Dao;
import ehcache.java.demo.db.dao.DaoFactory;
import ehcache.java.demo.person.Person;
import ehcache.java.demo.person.PersonCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonCacheTest {

    private InMemoryDatabase database;
    private PersonCache personCache;
    private Dao<Person> personDao;

    @BeforeEach
    void setUp() {
        final boolean loadTestData = true;
        database = new InMemoryDatabase(loadTestData);
        personCache = new PersonCache();
        personDao = DaoFactory.createPersonDao(database.getConnection(), personCache);
    }

    @Test
    void cacheShouldBeEmptyAfterInit() {
        assertThat(personCache.isEmpty()).isTrue();
    }

    @Test
    void shouldCachePersonOnFindById() {
        // arrange
        Person expected = new Person(1L, "John", "Kowalski", 44);

        // act
        personDao.findById(1L);

        // assert
        assertThat(personCache.get(1L)).hasValue(expected);
    }
}