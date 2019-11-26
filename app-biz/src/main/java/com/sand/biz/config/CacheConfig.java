package com.sand.biz.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    public static final String TOKEN_CACHE = "token";
    public static final String USER_CACHE = "user";

    private static final long CACHE_MAX_SIZE = 10000L;

    @Bean
    @Override
    public CacheManager cacheManager() {
        return caffeineCacheManager();
    }

    private SimpleCacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        CaffeineCache tokenCache = new CaffeineCache(TOKEN_CACHE,
                Caffeine.newBuilder()
                        .maximumSize(CACHE_MAX_SIZE)
                        .expireAfterWrite(2, TimeUnit.HOURS)
                        .build());

        CaffeineCache userCache = new CaffeineCache(USER_CACHE,
                Caffeine.newBuilder()
                        .maximumSize(CACHE_MAX_SIZE)
                        .expireAfterWrite(2, TimeUnit.DAYS)
                        .build());
        cacheManager.setCaches(Arrays.asList(tokenCache, userCache));
        return cacheManager;
    }
}
