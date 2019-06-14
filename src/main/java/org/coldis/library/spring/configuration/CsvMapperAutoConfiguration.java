package org.coldis.library.spring.configuration;

import org.coldis.library.serialization.csv.CsvHelper;
import org.coldis.library.spring.controller.CsvMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

/**
 * CSV mapper auto configuration.
 */
@Configuration
@ConditionalOnClass(CsvMapper.class)
public class CsvMapperAutoConfiguration {

	/**
	 * Creates a CSV mapper.
	 *
	 * @return CSV mapper.
	 */
	@Bean(name = { "csvMapper" })
	@Qualifier(value = "csvMapper")
	@ConditionalOnClass(value = CsvMapper.class)
	@ConditionalOnMissingBean(name = { "csvMapper" })
	public CsvMapper csvMapper() {
		return CsvHelper.getObjectMapper(DefaultAutoConfiguration.BASE_PACKAGE);
	}

	/**
	 * Creates a CSV message converter.
	 *
	 * @return A CSV message converter.
	 */
	@Bean(name = "csvMessageConverter")
	public HttpMessageConverter<?> csvMessageConverter() {
		return new CsvMessageConverter(this.csvMapper());
	}

}
