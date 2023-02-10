package org.coldis.library.test.spring.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Test.
 */
@EnableCaching
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CacheTest {

	/**
	 * Cached service.
	 */
	@Autowired
	private CachedService cachedService;

	/**
	 * Tests the local cache.
	 *
	 * @throws Exception If the test fails.
	 */
	@Test
	public void testLocalCache() throws Exception {
		final Integer attr1 = this.cachedService.getAttr1();
		Assertions.assertEquals(attr1, this.cachedService.getAttr1());
		Assertions.assertEquals(attr1, this.cachedService.getAttr1());
		Assertions.assertEquals(attr1, this.cachedService.getAttr1());
		Thread.sleep(23 * 1000);
		Assertions.assertEquals(attr1 + 1, this.cachedService.getAttr1());
		Assertions.assertEquals(attr1 + 1, this.cachedService.getAttr1());
		Assertions.assertEquals(attr1 + 1, this.cachedService.getAttr1());
	}

	/**
	 * Tests the local cache.
	 *
	 * @throws Exception If the test fails.
	 */
	@Test
	public void testCentralCache() throws Exception {
		final Integer attr2 = this.cachedService.getAttr2();
		Assertions.assertEquals(attr2, this.cachedService.getAttr2());
		Assertions.assertEquals(attr2, this.cachedService.getAttr2());
		Assertions.assertEquals(attr2, this.cachedService.getAttr2());
		Thread.sleep(23 * 1000);
		Assertions.assertEquals(attr2 + 1, this.cachedService.getAttr2());
		Assertions.assertEquals(attr2 + 1, this.cachedService.getAttr2());
		Assertions.assertEquals(attr2 + 1, this.cachedService.getAttr2());
	}

}
