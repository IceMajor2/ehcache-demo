package ehcache.java.demo.person.dao.impl;

import ehcache.java.demo.person.Person;
import ehcache.java.demo.person.dao.PersonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class PersonDaoImpl implements PersonDao {

    private final Connection connection;

    @Override
    public Person findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    Integer age = rs.getInt("age");
                    return new Person(id, firstName, lastName, age);
                }
            } catch (SQLException e) {
                log.error("Database access error occurred.");
            }
        } catch (SQLException e) {
            log.error("Database access error occurred.");
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public void save(Person person) {

    }
}
