package org.coldis.library.spring.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cache control auto configuration.
 */
@Configurable
public class CacheControlAutoConfiguration implements WebMvcConfigurer {

	/**
	 * Resources not to cache.
	 */
	@Value(value = "#{'${org.coldis.library.spring.cache.ignore}'.split(',')}")
	private String[] resourcesNotToCache;

	/**
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler(this.resourcesNotToCache).setCachePeriod(0).setCacheControl(CacheControl.noStore());

	}
}
