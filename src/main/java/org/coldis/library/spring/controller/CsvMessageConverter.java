package org.coldis.library.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

/**
 * CSV message converter.
 */
public class CsvMessageConverter extends AbstractJackson2HttpMessageConverter {

	/**
	 * Default constructor.
	 *
	 * @param objectMapper Object mapper.
	 */
	public CsvMessageConverter(final CsvMapper objectMapper) {
		super(objectMapper, new MediaType("text", "csv"), new MediaType("application", "csv"));
	}

}
