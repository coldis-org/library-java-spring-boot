package org.coldis.library.spring.configuration;

import java.util.Locale;

import org.coldis.library.service.controller.ControllerExceptionHandler;
import org.coldis.library.service.helper.AspectJAutoConfiguration;
import org.coldis.library.service.helper.CsvMapperAutoConfiguration;
import org.coldis.library.service.helper.JsonMapperAutoConfiguration;
import org.coldis.library.spring.installer.DataInstaller;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Default configuration.
 */
@Configuration
@ConditionalOnProperty(
		name = "org.coldis.configuration.default-enabled",
		havingValue = "true",
		matchIfMissing = true
)
@PropertySource(
		value = { DefaultAutoConfiguration.DEFAULT_PROPERTIES, DefaultAutoConfiguration.INTEGRATION_PROPERTIES },
		ignoreResourceNotFound = true
)
@AutoConfigureBefore(
		value = { AspectJAutoConfiguration.class, JsonMapperAutoConfiguration.class, CsvMapperAutoConfiguration.class, DateTimeFormatterAutoConfiguration.class,
				JmsAutoConfiguration.class, ValidatorAutoConfiguration.class, ControllerExceptionHandler.class, DataInstaller.class }
)
public class DefaultAutoConfiguration {

	/**
	 * Base package.
	 */
	public static final String BASE_PACKAGE = "org.coldis";

	/**
	 * Default properties.
	 */
	public static final String DEFAULT_PROPERTIES = "classpath:default.properties";

	/**
	 * Integration properties.
	 */
	public static final String INTEGRATION_PROPERTIES = "classpath:integration.properties";

}
