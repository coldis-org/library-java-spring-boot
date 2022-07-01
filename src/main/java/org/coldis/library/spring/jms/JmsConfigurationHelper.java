package org.coldis.library.spring.jms;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.JmsProperties.AcknowledgeMode;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.util.backoff.ExponentialBackOff;

/**
 * JMS configuration helper.
 */
public class JmsConfigurationHelper {

	/**
	 * Creates the JMS connection factory.
	 *
	 * @param  beanFactory Bean factory.
	 * @param  properties  JMS properties.
	 * @return             The JMS connection factory.
	 */
	public static ConnectionFactory createJmsConnectionFactory(
			final ListableBeanFactory beanFactory,
			final ArtemisProperties properties) {
		return JmsConfigurationHelper.createJmsConnectionFactory(beanFactory, properties);
	}

	/**
	 * Creates the JMS container factory.
	 *
	 * @param  connectionFactory      Connection factory.
	 * @param  destinationResolver    Destination resolver.
	 * @param  messageConverter       Message converter.
	 * @param  backoffInitialInterval Back-off initial interval
	 * @param  backoffMultiplier      Back-off multiplier.
	 * @param  backoffMaxInterval     Back-off max interval.
	 * @return                        The JMS container factory.
	 */
	public static DefaultJmsListenerContainerFactory createJmsContainerFactory(
			final ConnectionFactory connectionFactory,
			final DestinationResolver destinationResolver,
			final MessageConverter messageConverter,
			final Long backoffInitialInterval,
			final Double backoffMultiplier,
			final Long backoffMaxInterval) {
		// Creates a new container factory.
		final DefaultJmsListenerContainerFactory jmsContainerFactory = new DefaultJmsListenerContainerFactory();
		// Sets the default configuration.
		if (destinationResolver != null) {
			jmsContainerFactory.setDestinationResolver(destinationResolver);
		}
		if (messageConverter != null) {
			jmsContainerFactory.setMessageConverter(messageConverter);
		}
		jmsContainerFactory.setConnectionFactory(connectionFactory);
		jmsContainerFactory.setSessionTransacted(true);
		jmsContainerFactory.setAutoStartup(true);
		jmsContainerFactory.setSessionAcknowledgeMode(AcknowledgeMode.AUTO.getMode());
		if ((backoffInitialInterval != null) && (backoffMultiplier != null) && (backoffMaxInterval != null)) {
			final ExponentialBackOff backOff = new ExponentialBackOff(backoffInitialInterval, backoffMultiplier);
			backOff.setMaxInterval(backoffMaxInterval);
			jmsContainerFactory.setBackOff(backOff);
		}
		// Returns the container factory.
		return jmsContainerFactory;
	}

	/**
	 * Creates the JMS container factory.
	 *
	 * @param  connectionFactory      Connection factory.
	 * @param  destinationResolver    Destination resolver.
	 * @param  messageConverter       Message converter.
	 * @param  backoffInitialInterval Back-off initial interval
	 * @param  backoffMultiplier      Back-off multiplier.
	 * @param  backoffMaxInterval     Back-off max interval.
	 * @return                        The JMS container factory.
	 */
	public static DefaultJmsListenerContainerFactory createJmsTopicContainerFactory(
			final ConnectionFactory connectionFactory,
			final DestinationResolver destinationResolver,
			final MessageConverter messageConverter,
			final Long backoffInitialInterval,
			final Double backoffMultiplier,
			final Long backoffMaxInterval) {
		// Creates a new container factory.
		final DefaultJmsListenerContainerFactory jmsContainerFactory = JmsConfigurationHelper.createJmsContainerFactory(connectionFactory, destinationResolver,
				messageConverter, backoffInitialInterval, backoffMultiplier, backoffMaxInterval);
		jmsContainerFactory.setSubscriptionDurable(true);
		jmsContainerFactory.setSubscriptionShared(true);
		// Returns the container factory.
		return jmsContainerFactory;
	}

	/**
	 * Creates the JMS template.
	 *
	 * @param  connectionFactory   Connection factory.
	 * @param  destinationResolver Destination resolver.
	 * @param  messageConverter    Message converter.
	 * @return                     The JMS template.
	 */
	public static JmsTemplate createJmsTemplate(
			final ConnectionFactory connectionFactory,
			final DestinationResolver destinationResolver,
			final MessageConverter messageConverter) {
		// Creates the JMS template.
		final JmsTemplate jmsTemplate = new JmsTemplate();
		// Sets the default configuration.
		if (destinationResolver != null) {
			jmsTemplate.setDestinationResolver(destinationResolver);
		}
		if (messageConverter != null) {
			jmsTemplate.setMessageConverter(messageConverter);
		}
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setSessionAcknowledgeMode(AcknowledgeMode.AUTO.getMode());
		// Returns the configured JMS template.
		return jmsTemplate;
	}

	/**
	 * Creates the JMS template.
	 *
	 * @param  connectionFactory   Connection factory.
	 * @param  destinationResolver Destination resolver.
	 * @param  messageConverter    Message converter.
	 * @return                     The JMS template.
	 */
	public static JmsTemplate createJmsTopicTemplate(
			@Qualifier(value = "communicationServiceJmsConnectionFactory")
			final ConnectionFactory connectionFactory,
			final DestinationResolver destinationResolver,
			final MessageConverter messageConverter) {
		// Creates the JMS template.
		final JmsTemplate jmsTemplate = JmsConfigurationHelper.createJmsTemplate(connectionFactory, destinationResolver, messageConverter);
		jmsTemplate.setPubSubDomain(true);
		// Returns the configured JMS template.
		return jmsTemplate;
	}

}
