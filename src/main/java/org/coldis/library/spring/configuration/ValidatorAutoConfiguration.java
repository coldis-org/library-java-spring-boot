package org.coldis.library.spring.configuration;

import javax.validation.Validator;

import org.coldis.library.helper.ExtendedValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Validator auto configuration.
 */
@Configuration
public class ValidatorAutoConfiguration {

	/**
	 * Creates the internal validator.
	 *
	 * @return The internal validator.
	 */
	@Bean(name = "internalValidator")
	public Validator createInternalValidator() {
		return new LocalValidatorFactoryBean();
	}

	/**
	 * Creates the extended validator.
	 *
	 * @return The extended validator.
	 */
	@Primary
	@Bean(name = "extendedValidator")
	public ExtendedValidator createExtendedValidator() {
		return new ExtendedValidator(this.createInternalValidator());
	}

}
