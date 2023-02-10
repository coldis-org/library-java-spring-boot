package org.coldis.library.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cache control auto configuration.
 */
@Configuration
public class ResourceCacheControlAutoConfiguration implements WebMvcConfigurer {

	/**
	 * Resource properties.
	 */
	@Autowired
	private ResourceProperties resourceProperties;

	/**
	 * Resources not to cache.
	 */
	@Value(value = "#{'${org.coldis.library.spring.cache.ignore}'.split(',')}")
	private String[] resourcesNotToCache;

	/**
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(
			final ResourceHandlerRegistry registry) {
		registry.addResourceHandler(this.resourcesNotToCache).addResourceLocations(this.resourceProperties.getStaticLocations())
				.setCacheControl(CacheControl.noStore().mustRevalidate()).setCachePeriod(0);
	}
}
