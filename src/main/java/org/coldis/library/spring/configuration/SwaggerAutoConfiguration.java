package org.coldis.library.spring.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger auto configuration.
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "environment", havingValue = "development", matchIfMissing = false)
public class SwaggerAutoConfiguration {

	/**
	 * Creates the API documentation.
	 *
	 * @return API documentation.
	 */
	@Bean(name = "apiDocumentation")
	public Docket createApiDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

}