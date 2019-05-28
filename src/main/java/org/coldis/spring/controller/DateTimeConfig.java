package org.coldis.spring.controller;

import org.coldis.library.helper.DateTimeHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

/**
 * Date/time configuration.
 */
@Configuration
class DateTimeConfiguration {

	/**
	 * Creates the conversion service.
	 *
	 * @return The conversion service.
	 */
	@Bean(name = "conversionService")
	public FormattingConversionService createConversionService() {
		// Creates the default service.
		final DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);
		// Registers the date/time formatters to the service.
		final DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setDateFormatter(DateTimeHelper.DATE_TIME_FORMATTER);
		registrar.setDateTimeFormatter(DateTimeHelper.DATE_TIME_FORMATTER);
		registrar.registerFormatters(conversionService);
		// Returns the created service.
		return conversionService;
	}
}