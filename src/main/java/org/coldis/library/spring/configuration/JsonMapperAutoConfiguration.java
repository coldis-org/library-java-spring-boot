package org.coldis.library.spring.configuration;

import org.apache.commons.lang3.ArrayUtils;
import org.coldis.library.serialization.ObjectMapperHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON mapper auto configuration.
 */
@Configuration
@ConditionalOnClass({ ObjectMapper.class, Jackson2ObjectMapperBuilder.class })
public class JsonMapperAutoConfiguration {

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
	@Bean(name = { "objectMapper", "jsonMapper" })
	public ObjectMapper jsonObjectMapper(final Jackson2ObjectMapperBuilder builder) {
		// Creates the object mapper.
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		// Registers the date/time module.
		objectMapper.registerModule(ObjectMapperHelper.getDateTimeModule());
		// Registers the subtypes from the base packages.
		objectMapper = ObjectMapperHelper.addSubtypesFromPackage(objectMapper, DefaultAutoConfiguration.BASE_PACKAGE);
		// For each type package.
		if (!ArrayUtils.isEmpty(this.jsonTypePackages)) {
			// For each type package.
			for (final String typePackage : this.jsonTypePackages) {
				// Registers the subtypes from the base packages.
				objectMapper = ObjectMapperHelper.addSubtypesFromPackage(objectMapper, typePackage);
			}
		}
		// Returns the configured object mapper.
		return objectMapper;
	}

}
