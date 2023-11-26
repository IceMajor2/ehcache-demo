package ehcache.java.demo.cache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;

public class CacheHelper {

    private static CacheManager cacheManager;

    private CacheHelper() {}

    public static CacheManager getInstanceOfCacheManager() {
        if (cacheManager == null) {
            cacheManager = CacheManagerBuilder
                    .newCacheManagerBuilder()
                    .build();
            cacheManager.init();
            return cacheManager;
        }
        return cacheManager;
    }
}
