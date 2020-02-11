package org.coldis.library.test.spring;

import org.coldis.library.spring.AbstractSpringApplication;
import org.coldis.library.spring.configuration.DefaultAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Test application.
 */
@SpringBootApplication(scanBasePackages = { DefaultAutoConfiguration.BASE_PACKAGE })
public class TestApplication extends AbstractSpringApplication {

	/**
	 * Runs the Spring application.
	 *
	 * @param arguments Initialization arguments.
	 */
	public static void main(final String[] arguments) {
		AbstractSpringApplication.run(TestApplication.class, arguments);
	}

}
