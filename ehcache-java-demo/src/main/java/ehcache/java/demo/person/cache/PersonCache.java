package ehcache.java.demo.person.cache;

import ehcache.java.demo.person.Person;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.Optional;

public class PersonCache {

    private CacheManager cacheManager;
    private Cache<Long, Person> personCache;

    public PersonCache() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();
        personCache = createPersonCache();
    }

    private Cache<Long, Person> createPersonCache() {
        return cacheManager
                .createCache("person", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(
                                Long.class, Person.class,
                                ResourcePoolsBuilder.heap(10)
                        ));
    }

    public Optional<Person> get(Long id) {
        return Optional.ofNullable(personCache.get(id));
    }

    public boolean isEmpty() {
        return !personCache.iterator().hasNext();
    }

    public void put(Person person) {
        personCache.put(person.id(), person);
    }
}
