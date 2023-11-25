package ehcache.java.demo;

import ehcache.java.demo.db.InMemoryDatabase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    private static final boolean LOAD_TEST_DATA = true;

    public static void main(String[] args) {
        InMemoryDatabase database = new InMemoryDatabase(LOAD_TEST_DATA);
        while(true);
    }
}
