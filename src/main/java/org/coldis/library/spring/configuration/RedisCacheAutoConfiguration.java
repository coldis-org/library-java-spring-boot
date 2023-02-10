package org.coldis.library.spring.configuration;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Cache configuration.
 */
@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
@ConditionalOnClass(value = { CacheManager.class, RedisCacheManager.class })
public class RedisCacheAutoConfiguration {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAutoConfiguration.class);

	/**
	 * Serialization pair.
	 */
	private final SerializationPair<Object> serializationPair;

	/**
	 * Cache manager.
	 */
	private RedisCacheManager millisExpirationCentralCacheManager;

	/**
	 * Cache manager.
	 */
	private RedisCacheManager secondsExpirationCentralCacheManager;

	/**
	 * Cache manager.
	 */
	private RedisCacheManager minutesExpirationCentralCacheManager;

	/**
	 * Cache manager.
	 */
	private RedisCacheManager hoursExpirationCentralCacheManager;

	/**
	 * Cache manager.
	 */
	private RedisCacheManager dayExpirationCentralCacheManager;

	/**
	 * Default constructor.
	 */
	public RedisCacheAutoConfiguration(final ObjectMapper objectMapper) {
		this.serializationPair = SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
	}

	/**
	 * Short lived cache.
	 *
	 * @return Short lived cache.
	 */
	@Bean
	public CacheManager millisExpirationCentralCacheManager(
			final RedisConnectionFactory redisConnectionFactory) {
		this.millisExpirationCentralCacheManager = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(3100)).serializeValuesWith(this.serializationPair))
				.build();
		return this.millisExpirationCentralCacheManager;
	}

	/**
	 * Short lived cache.
	 *
	 * @return Short lived cache.
	 */
	@Bean
	public CacheManager secondsExpirationCentralCacheManager(
			final RedisConnectionFactory redisConnectionFactory) {
		this.secondsExpirationCentralCacheManager = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(23)).serializeValuesWith(this.serializationPair))
				.build();
		return this.secondsExpirationCentralCacheManager;
	}

	/**
	 * Short lived cache.
	 *
	 * @return Short lived cache.
	 */
	@Bean
	public CacheManager minutesExpirationCentralCacheManager(
			final RedisConnectionFactory redisConnectionFactory) {
		this.minutesExpirationCentralCacheManager = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(11)).serializeValuesWith(this.serializationPair))
				.build();
		return this.minutesExpirationCentralCacheManager;
	}

	/**
	 * Long lived cache.
	 *
	 * @return Long lived cache.
	 */
	@Bean
	public CacheManager hoursExpirationCentralCacheManager(
			final RedisConnectionFactory redisConnectionFactory) {
		this.hoursExpirationCentralCacheManager = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(3)).serializeValuesWith(this.serializationPair)).build();
		return this.hoursExpirationCentralCacheManager;
	}

	/**
	 * Long lived cache.
	 *
	 * @return Long lived cache.
	 */
	@Bean
	public CacheManager dayExpirationCentralCacheManager(
			final RedisConnectionFactory redisConnectionFactory) {
		this.dayExpirationCentralCacheManager = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)).serializeValuesWith(this.serializationPair)).build();
		return this.dayExpirationCentralCacheManager;
	}

}
