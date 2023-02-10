package org.coldis.library.spring.configuration;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * Cache configuration.
 */
@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
@ConditionalOnClass(value = { CacheManager.class, CaffeineCacheManager.class })
public class LocalCacheConfiguration {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalCacheConfiguration.class);

	/**
	 * Cache manager.
	 */
	private CaffeineCacheManager millisExpirationLocalCacheManager;

	/**
	 * Cache manager.
	 */
	private CaffeineCacheManager secondsExpirationLocalCacheManager;

	/**
	 * Cache manager.
	 */
	private CaffeineCacheManager minutesExpirationLocalCacheManager;

	/**
	 * Cache manager.
	 */
	private CaffeineCacheManager hoursExpirationLocalCacheManager;

	/**
	 * Cache manager.
	 */
	private CaffeineCacheManager dayExpirationLocalCacheManager;

	/**
	 * Short lived cache.
	 *
	 * @return Short lived cache.
	 */
	@Bean
	public CacheManager millisExpirationLocalCacheManager() {
		this.millisExpirationLocalCacheManager = new CaffeineCacheManager();
		this.millisExpirationLocalCacheManager.setCaffeine(Caffeine.newBuilder().recordStats().expireAfterWrite(Duration.ofMillis(3100)).maximumSize(13791));
		return this.millisExpirationLocalCacheManager;
	}

	/**
	 * Short lived cache.
	 *
	 * @return Short lived cache.
	 */
	@Bean
	public CacheManager secondsExpirationLocalCacheManager() {
		this.secondsExpirationLocalCacheManager = new CaffeineCacheManager();
		this.secondsExpirationLocalCacheManager.setCaffeine(Caffeine.newBuilder().recordStats().expireAfterWrite(Duration.ofSeconds(23)).maximumSize(13791));
		return this.secondsExpirationLocalCacheManager;
	}

	/**
	 * Short lived cache.
	 *
	 * @return Short lived cache.
	 */
	@Bean
	@Primary
	public CacheManager minutesExpirationLocalCacheManager() {
		this.minutesExpirationLocalCacheManager = new CaffeineCacheManager();
		this.minutesExpirationLocalCacheManager.setCaffeine(Caffeine.newBuilder().recordStats().expireAfterWrite(Duration.ofMinutes(11)).maximumSize(13791));
		return this.minutesExpirationLocalCacheManager;
	}

	/**
	 * Long lived cache.
	 *
	 * @return Long lived cache.
	 */
	@Bean
	public CacheManager hoursExpirationLocalCacheManager() {
		this.hoursExpirationLocalCacheManager = new CaffeineCacheManager();
		this.hoursExpirationLocalCacheManager.setCaffeine(Caffeine.newBuilder().recordStats().expireAfterWrite(Duration.ofHours(3)).maximumSize(13791));
		return this.hoursExpirationLocalCacheManager;
	}

	/**
	 * Long lived cache.
	 *
	 * @return Long lived cache.
	 */
	@Bean
	public CacheManager dayExpirationLocalCacheManager() {
		this.dayExpirationLocalCacheManager = new CaffeineCacheManager();
		this.dayExpirationLocalCacheManager.setCaffeine(Caffeine.newBuilder().recordStats().expireAfterWrite(Duration.ofDays(1)).maximumSize(13791));
		return this.dayExpirationLocalCacheManager;
	}

	/**
	 * Logs stats.
	 */
	@Scheduled(cron = "0 */5 * * * *")
	public void logStats() {
		this.millisExpirationLocalCacheManager.getCacheNames()
				.forEach(name -> LocalCacheConfiguration.LOGGER.debug("Cache '" + name + "' estimated size in '"
						+ ((CaffeineCache) this.millisExpirationLocalCacheManager.getCache(name)).getNativeCache().estimatedSize() + "' and stats: "
						+ ((CaffeineCache) this.millisExpirationLocalCacheManager.getCache(name)).getNativeCache().stats().toString()));
		this.secondsExpirationLocalCacheManager.getCacheNames()
				.forEach(name -> LocalCacheConfiguration.LOGGER.debug("Cache '" + name + "' estimated size in '"
						+ ((CaffeineCache) this.secondsExpirationLocalCacheManager.getCache(name)).getNativeCache().estimatedSize() + "' and stats: "
						+ ((CaffeineCache) this.secondsExpirationLocalCacheManager.getCache(name)).getNativeCache().stats().toString()));
		this.minutesExpirationLocalCacheManager.getCacheNames()
				.forEach(name -> LocalCacheConfiguration.LOGGER.debug("Cache '" + name + "' estimated size in '"
						+ ((CaffeineCache) this.minutesExpirationLocalCacheManager.getCache(name)).getNativeCache().estimatedSize() + "' and stats: "
						+ ((CaffeineCache) this.minutesExpirationLocalCacheManager.getCache(name)).getNativeCache().stats().toString()));
		this.hoursExpirationLocalCacheManager.getCacheNames()
				.forEach(name -> LocalCacheConfiguration.LOGGER.debug("Cache '" + name + "' estimated size in '"
						+ ((CaffeineCache) this.hoursExpirationLocalCacheManager.getCache(name)).getNativeCache().estimatedSize() + "' and stats: "
						+ ((CaffeineCache) this.hoursExpirationLocalCacheManager.getCache(name)).getNativeCache().stats().toString()));
		this.dayExpirationLocalCacheManager.getCacheNames()
				.forEach(name -> LocalCacheConfiguration.LOGGER.debug("Cache '" + name + "' estimated size in '"
						+ ((CaffeineCache) this.dayExpirationLocalCacheManager.getCache(name)).getNativeCache().estimatedSize() + "' and stats: "
						+ ((CaffeineCache) this.dayExpirationLocalCacheManager.getCache(name)).getNativeCache().stats().toString()));
	}

}
