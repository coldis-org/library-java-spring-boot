package org.coldis.library.spring.configuration;

import java.util.Locale;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(name = "org.coldis.configuration.default-enabled", havingValue = "true", matchIfMissing = true)
@PropertySource(
		value = { DefaultAutoConfiguration.DEFAULT_PROPERTIES, DefaultAutoConfiguration.INTEGRATION_PROPERTIES },
		ignoreResourceNotFound = true)
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

	/**
	 * Creates the locale resolver.
	 *
	 * @return The locale resolver.
	 */
	@Primary
	@Bean(name = "localeResolver")
	public LocaleResolver createLocaleResolver() {
		// Creates the locale resolver.
		final SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		// Default locale is US.
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		// Returns the configured locale resolver.
		return sessionLocaleResolver;
	}

	/**
	 * Creates the message source.
	 *
	 * @return The message source.
	 */
	@Primary
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource createMessageSource() {
		// Creates the message source.
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// Sets the base path and expiration (30 minutes).
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setCacheSeconds(1800);
		// Returns the configured message source.
		return messageSource;
	}

}
