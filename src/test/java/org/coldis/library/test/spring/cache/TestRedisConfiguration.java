package org.coldis.library.test.spring.cache;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;

import redis.embedded.RedisServer;

@Configuration
public class TestRedisConfiguration {

	private final RedisServer redisServer;

	public TestRedisConfiguration(final RedisProperties redisProperties) {
		this.redisServer = new RedisServer(redisProperties.getPort());
	}

	@PostConstruct
	public void postConstruct() {
		this.redisServer.start();
	}

	@PreDestroy
	public void preDestroy() {
		this.redisServer.stop();
	}
}
