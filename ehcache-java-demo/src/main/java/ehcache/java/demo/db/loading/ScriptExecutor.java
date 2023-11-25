package ehcache.java.demo.db.loading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;

@Slf4j
public class ScriptExecutor {

    private static final boolean CONTINUE_ON_ERROR = false;
    private static final boolean IGNORE_FAILED_DROPS = false;
    private static final String COMMENT_PREFIX = "--";
    private static final String SEPARATOR = ";";
    private static final String BLOCK_COMMENT_START_DELIMITER = "/*";
    private static final String BLOCK_COMMENT_END_DELIMITER = "*/";

    public static boolean runScript(String path, Connection connection) {
        ScriptUtils.executeSqlScript(
                connection,
                new EncodedResource(new PathResource(path)),
                CONTINUE_ON_ERROR,
                IGNORE_FAILED_DROPS,
                COMMENT_PREFIX,
                SEPARATOR,
                BLOCK_COMMENT_START_DELIMITER,
                BLOCK_COMMENT_END_DELIMITER
        );
        return true;
    }
}
