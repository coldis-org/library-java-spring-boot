package org.coldis.library.test.spring.cache;

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
	 * Attribute.
	 */
	public static Integer ATTR_2 = 0;

	/**
	 * Gets the aTTR_1.
	 *
	 * @return The aTTR_1.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationCentralCacheManager",
			value = "cachedService-getFromCentralCache"
	)
	public CacheSimpleObject getFromCentralCache() {
		CachedService.ATTR_1++;
		return new CacheSimpleObject(CachedService.ATTR_1);
	}

	/**
	 * Gets the aTTR_2.
	 *
	 * @return The aTTR_2.
	 */
	@Cacheable(
			cacheManager = "secondsExpirationLocalCacheManager",
			value = "cachedService-getFromLocalCache"
	)
	public Integer getFromLocalCache() {
		CachedService.ATTR_2++;
		return CachedService.ATTR_2;
	}

}
