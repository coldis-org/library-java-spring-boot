package org.coldis.library.test.spring.jms;

import javax.jms.ConnectionFactory;

import org.coldis.library.spring.jms.JmsConfigurationHelper;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.boot.autoconfigure.jms.artemis.ExtendedArtemisConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.stereotype.Component;

/**
 * JMS configuration.
 */
@EnableJms
@Configuration
@Import(value = { ExtendedArtemisConfiguration.class })
public class JmsConfiguration {

	/**
	 * Message converter.
	 */
	@Autowired(required = false)
	private MessageConverter messageConverter;

	/**
	 * JMS destination resolver.
	 */
	@Autowired(required = false)
	private DestinationResolver destinationResolver;

	@Component
	public class TestArtemisProperties extends ArtemisProperties {

	}

	/**
	 * Creates the JMS connection factory.
	 *
	 * @param  beanFactory Bean factory.
	 * @param  properties  JMS properties.
	 * @return             The JMS connection factory.
	 */
	@Bean
	public ConnectionFactory createJmsConnectionFactory(
			final ListableBeanFactory beanFactory,
			final ArtemisProperties properties) {
		return JmsConfigurationHelper.createJmsConnectionFactory(beanFactory, properties);
	}

	/**
	 * Creates the JMS container factory.
	 *
	 * @param  connectionFactory Connection factory.
	 * @return                   The JMS container factory.
	 */
	@Bean
	public DefaultJmsListenerContainerFactory createJmsContainerFactory(
			final ConnectionFactory connectionFactory) {
		return JmsConfigurationHelper.createJmsContainerFactory(connectionFactory, this.destinationResolver, this.messageConverter, 1000L, 3.1D, 1000L * 180L);
	}

	/**
	 * Creates the JMS template.
	 *
	 * @param  connectionFactory Connection factory.
	 * @return                   The JMS template.
	 */
	@Bean
	public JmsTemplate createJmsTemplate(
			final ConnectionFactory connectionFactory) {
		return JmsConfigurationHelper.createJmsTemplate(connectionFactory, this.destinationResolver, this.messageConverter);
	}

}