package org.coldis.library.spring.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;

/**
 * AspectJ auto configuration.
 */
@Configuration
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
@ConditionalOnProperty(name = "org.coldis.configuration.aspectj-enabled", havingValue = "true", matchIfMissing = true)
public class AspectJAutoConfiguration {

}