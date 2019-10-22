package org.coldis.library.spring.configuration;

import org.apache.commons.lang3.ArrayUtils;
import org.coldis.library.serialization.ObjectMapperHelper;
import org.coldis.library.serialization.csv.CsvHelper;
import org.coldis.library.spring.controller.CsvMessageConverter;
import org.coldis.library.spring.controller.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

/**
 * CSV mapper auto configuration.
 */
@Configuration
public class ObjectMapperAutoConfiguration {

	/**
	 * JSON type packages.
	 */
	@Value(value = "#{'${org.coldis.configuration.base-package}'.split(',')}")
	private String[] jsonTypePackages;

	/**
	 * Creates the JSON object mapper.
	 *
	 * @param  builder JSON object mapper builder.
	 * @return         The JSON object mapper.
	 */
	@Primary
	@Qualifier(value = "jsonMapper")
	@Bean(name = { "objectMapper", "jsonMapper" })
	@ConditionalOnClass({ ObjectMapper.class, Jackson2ObjectMapperBuilder.class })
	public ObjectMapper createJsonMapper() {
		// Creates the object mapper.
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
		// Registers the date/time module.
		objectMapper.registerModule(ObjectMapperHelper.getDateTimeModule());
		// Registers the subtypes from the base packages.
		objectMapper = ObjectMapperHelper.addSubtypesFromPackage(objectMapper,
				ArrayUtils.add(this.jsonTypePackages, DefaultAutoConfiguration.BASE_PACKAGE));
		// Returns the configured object mapper.
		return objectMapper;
	}

	/**
	 * Creates a JSON message converter.
	 *
	 * @return A JSON message converter.
	 */
	@Primary
	@Bean(name = "jsonMessageConverter")
	public HttpMessageConverter<?> createJsonMessageConverter() {
		return new JsonMessageConverter(this.createJsonMapper());
	}

	/**
	 * Creates a CSV mapper.
	 *
	 * @return CSV mapper.
	 */
	@Qualifier(value = "csvMapper")
	@Bean(name = { "csvMapper" })
	public CsvMapper createCsvMapper() {
		return CsvHelper.getObjectMapper(ArrayUtils.add(this.jsonTypePackages, DefaultAutoConfiguration.BASE_PACKAGE));
	}

	/**
	 * Creates a CSV message converter.
	 *
	 * @return A CSV message converter.
	 */
	@Bean(name = "csvMessageConverter")
	public HttpMessageConverter<?> createCsvMessageConverter() {
		return new CsvMessageConverter(this.createCsvMapper());
	}

}
