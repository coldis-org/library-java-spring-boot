package org.coldis.library.spring.configuration;

import org.apache.commons.lang3.ArrayUtils;
import org.coldis.library.serialization.csv.CsvHelper;
import org.coldis.library.spring.controller.CsvMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

/**
 * CSV mapper auto configuration.
 */
@Configuration
@ConditionalOnClass(value = CsvMapper.class)
public class CsvMapperAutoConfiguration {

	/**
	 * JSON type packages.
	 */
	@Value(value = "#{'${org.coldis.configuration.base-package}'.split(',')}")
	private String[] jsonTypePackages;

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
