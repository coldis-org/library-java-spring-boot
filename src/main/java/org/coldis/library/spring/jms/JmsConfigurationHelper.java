package org.coldis.library.spring.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.JmsPoolConnectionFactoryFactory;
import org.springframework.boot.autoconfigure.jms.JmsProperties.AcknowledgeMode;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.boot.autoconfigure.jms.artemis.ExtensibleArtemisConnectionFactoryFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.util.ErrorHandler;
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
		return new JmsPoolConnectionFactoryFactory(properties.getPool()).createPooledConnectionFactory(
				new ExtensibleArtemisConnectionFactoryFactory(beanFactory, properties).createConnectionFactory(ActiveMQConnectionFactory.class));
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
			final ErrorHandler errorHandler,
			final Long backoffInitialInterval,
			final Double backoffMultiplier,
			final Long backoffMaxElapsedTime) {
		// Creates a new container factory.
		final DefaultJmsListenerContainerFactory jmsContainerFactory = new DefaultJmsListenerContainerFactory();
		// Sets the default configuration.
		if (destinationResolver != null) {
			jmsContainerFactory.setDestinationResolver(destinationResolver);
		}
		if (messageConverter != null) {
			jmsContainerFactory.setMessageConverter(messageConverter);
		}
		if (errorHandler != null) {
			jmsContainerFactory.setErrorHandler(errorHandler);
		}
		jmsContainerFactory.setConnectionFactory(connectionFactory);
		jmsContainerFactory.setSessionTransacted(true);
		jmsContainerFactory.setAutoStartup(true);
		jmsContainerFactory.setSessionAcknowledgeMode(AcknowledgeMode.AUTO.getMode());
		if ((backoffInitialInterval != null) && (backoffMultiplier != null) && (backoffMaxElapsedTime != null)) {
			final ExponentialBackOff backOff = new ExponentialBackOff(backoffInitialInterval, backoffMultiplier);
			backOff.setMaxElapsedTime(backoffMaxElapsedTime);
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
	 * @param  backoffMaxElapsedTime  Back-off max interval.
	 * @return                        The JMS container factory.
	 */
	public static DefaultJmsListenerContainerFactory createJmsTopicContainerFactory(
			final ConnectionFactory connectionFactory,
			final DestinationResolver destinationResolver,
			final MessageConverter messageConverter,
			final ErrorHandler errorHandler,
			final Long backoffInitialInterval,
			final Double backoffMultiplier,
			final Long backoffMaxElapsedTime) {
		// Creates a new container factory.
		final DefaultJmsListenerContainerFactory jmsContainerFactory = JmsConfigurationHelper.createJmsContainerFactory(connectionFactory, destinationResolver,
				messageConverter, errorHandler, backoffInitialInterval, backoffMultiplier, backoffMaxElapsedTime);
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
