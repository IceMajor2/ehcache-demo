package com.example.ehcache.spring.demo;

import com.example.ehcache.spring.demo.person.Person;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager ehcacheManager() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        createPersonCache(cacheManager);
        return cacheManager;
    }

    private void createPersonCache(CacheManager cacheManager) {
        CacheConfiguration<Long, Person> config = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Long.class, Person.class,
                        ResourcePoolsBuilder
                                .heap(1000)
                                .newResourcePoolsBuilder()
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofHours(1)))
                .build();
        javax.cache.configuration.Configuration<Long, Person> configuration = Eh107Configuration
                .fromEhcacheCacheConfiguration(config);
        cacheManager.createCache("personCache", configuration);
    }
}
