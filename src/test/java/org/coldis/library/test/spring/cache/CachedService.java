package org.coldis.library.test.spring.cache;

import java.math.BigDecimal;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Cache service.
 */
@Service
public class CachedService {

	/**
	 * Attribute.
	 */
	public static Integer ATTR_1 = 0;

	/**
	 * Gets the aTTR_1.
	 *
	 * @return The aTTR_1.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationCentralCacheManager",
			value = "cachedService-getFromCentralCache1"
	)
	public Integer getFromCentralCache1() {
		CachedService.ATTR_1++;
		return CachedService.ATTR_1;
	}

	/**
	 * Gets the aTTR_1.
	 *
	 * @return The aTTR_1.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationCentralCacheManager",
			value = "cachedService-getFromCentralCache2"
	)
	public CacheSimpleObject getFromCentralCache2() {
		CachedService.ATTR_1++;
		return new CacheSimpleObject(CachedService.ATTR_1);
	}

	/**
	 * Gets the aTTR_1.
	 *
	 * @return The aTTR_1.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationCentralCacheManager",
			value = "cachedService-getFromCentralCache3"
	)
	public BigDecimal getFromCentralCache3() {
		CachedService.ATTR_1++;
		return new BigDecimal(CachedService.ATTR_1);
	}

	/**
	 * Gets the aTTR_1.
	 *
	 * @return The aTTR_1.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationLocalCacheManager",
			value = "cachedService-getFromLocalCache1"
	)
	public Integer getFromLocalCache1() {
		CachedService.ATTR_1++;
		return CachedService.ATTR_1;
	}

	/**
	 * Gets the aTTR_1.
	 *
	 * @return The aTTR_1.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationLocalCacheManager",
			value = "cachedService-getFromLocalCache2"
	)
	public CacheSimpleObject getFromLocalCache2() {
		CachedService.ATTR_1++;
		return new CacheSimpleObject(CachedService.ATTR_1);
	}

	/**
	 * Gets the aTTR_1.
	 *
	 * @return The aTTR_1.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationLocalCacheManager",
			value = "cachedService-getFromLocalCache3"
	)
	public BigDecimal getFromLocalCache3() {
		CachedService.ATTR_1++;
		return new BigDecimal(CachedService.ATTR_1);
	}

}
