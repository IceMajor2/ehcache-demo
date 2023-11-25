package ehcache.java.demo.db;

import ehcache.java.demo.db.dao.Dao;
import ehcache.java.demo.db.dao.DaoFactory;
import ehcache.java.demo.person.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryDatabaseTest {

    @Test
    void whenConstructingObject_thenLoadPersonTable() {
        // arrange
        InMemoryDatabase database = new InMemoryDatabase(true);
        Dao<Person> personDao = DaoFactory.createPersonDao(database.getConnection());
        List<Person> expected = List.of(
                new Person(1L, "John", "Kowalski", 44),
                new Person(2L, "Guybrush", "Threepwood", 25),
                new Person(3L, "Toby", "Randall", 31),
                new Person(4L, "Tina", "Swain", 28),
                new Person(5L, "Marco", "Lister", 81),
                new Person(6L, "Robert", "Koch", 80)
        );

        // act
        List<Person> actual = personDao.findAll();

        // assert
        assertThat(actual)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expected);
    }

}