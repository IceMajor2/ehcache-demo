package ehcache.java.demo.person.cache;

import ehcache.java.demo.cache.CacheHelper;
import ehcache.java.demo.person.Person;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class PersonCache {

    private CacheManager cacheManager;
    private Cache<Long, Person> personCache;

    public PersonCache() {
        cacheManager = CacheHelper.getInstanceOfCacheManager();
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

    public boolean isEmpty() {
        return !personCache.iterator().hasNext();
    }
}
