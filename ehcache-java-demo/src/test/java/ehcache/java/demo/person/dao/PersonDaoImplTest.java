package ehcache.java.demo.person.dao;

import ehcache.java.demo.person.Person;
import ehcache.java.demo.person.PersonDaoImpl;
import ehcache.java.demo.person.PersonCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Optional;

import static org.mockito.Mockito.*;

class PersonDaoImplTest {

    private Connection connection;
    private PersonCache personCache;
    private PersonDaoImpl SUT;

    @BeforeEach
    void setUp() {
        connection = mock(Connection.class);
        personCache = mock(PersonCache.class);
        SUT = new PersonDaoImpl(connection, personCache);
    }

    @Test
    void shouldReturnAnEntityFromCache() {
        // arrange
        when(personCache.get(1L)).thenReturn(Optional.of(new Person(1L, "ANY_NAME", "ANY_NAME", 99)));

        // act
        SUT.findById(1L);

        // assert
        verifyNoInteractions(connection);
    }
}