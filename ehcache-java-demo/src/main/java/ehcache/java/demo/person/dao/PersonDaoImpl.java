package ehcache.java.demo.person.dao;

import ehcache.java.demo.db.dao.Dao;
import ehcache.java.demo.person.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class PersonDaoImpl implements Dao<Person> {

    private final Connection connection;

    @Override
    public Person findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Long actualId = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Integer age = rs.getInt("age");
                return new Person(actualId, firstName, lastName, age);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM person")) {
            ResultSet rs = statement.executeQuery();
            List<Person> people = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Integer age = rs.getInt("age");
                people.add(new Person(id, firstName, lastName, age));
            }
            log.info("Retrieved {} people from database.", people.size());
            return people;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Person person) {

    }
}
