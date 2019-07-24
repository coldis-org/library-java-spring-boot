package org.coldis.library.test.spring.health;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * Health check service test.
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class HealthCheckServiceTest {

	/**
	 * Health check service client.
	 */
	@Autowired
	private HealthCheckServiceClient checkEntityServiceClient;

	/**
	 * Tests the check service.
	 *
	 * @throws Exception If the test fails.
	 */
	@Test
	public void testCheck() throws Exception {
		Assert.assertEquals(1, this.checkEntityServiceClient.check().intValue());
	}
}