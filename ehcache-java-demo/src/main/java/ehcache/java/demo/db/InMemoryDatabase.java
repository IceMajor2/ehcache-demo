package ehcache.java.demo.db;

import ehcache.java.demo.db.loading.DataLoader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Getter
public class InMemoryDatabase {

    private static final String H2_URL = "jdbc:h2:mem:test";
    private Connection connection;

    public InMemoryDatabase(boolean loadTestData) {
        establishConnection();
        DataLoader.executeScript(connection, "schema.sql");
        if (loadTestData)
            DataLoader.executeScript(connection, "data.sql");
    }

    private void establishConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(H2_URL);
            log.info("Connected to in-memory H2 database.");
            this.connection = connection;
        } catch (SQLException e) {
            log.error("Unable to connect with the database");
        }
    }
}
