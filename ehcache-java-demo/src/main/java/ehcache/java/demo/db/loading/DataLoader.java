package ehcache.java.demo.db.loading;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.sql.Connection;

@Slf4j
public class DataLoader {

    public static boolean executeScript(Connection connection, String sqlFilename) {
        if(connection != null) {
            String path = new File(ClassLoader.getSystemClassLoader()
                    .getResource(sqlFilename).getPath()).getPath();
            ScriptExecutor.runScript(path, connection);
            return true;
        } else {
            log.warn("Unable to execute [{}] script. No database connection found.", sqlFilename);
            return false;
        }
    }
}
