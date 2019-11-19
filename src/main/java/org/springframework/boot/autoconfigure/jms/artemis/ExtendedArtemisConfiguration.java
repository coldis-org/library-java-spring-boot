package org.springframework.boot.autoconfigure.jms.artemis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Artemis extended configuration.
 */
@Configuration
@Import({ ArtemisEmbeddedServerConfiguration.class })
public class ExtendedArtemisConfiguration {

}
