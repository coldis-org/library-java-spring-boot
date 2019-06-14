package org.coldis.library.spring.configuration;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DestinationResolver;

/**
 * JMS auto configuration.
 */
@EnableJms
@Configuration
@ConditionalOnClass(JmsTemplate.class)
public class JmsAutoConfiguration {

	/**
	 * JMS connection factory.
	 */
	@Autowired
	private ConnectionFactory connectionFactory;

	/**
	 * JMS destination resolver.
	 */
	@Autowired(required = false)
	private DestinationResolver destinationResolver;

	/**
	 * Creates a transacted JMS queue template.
	 *
	 * @return Transacted JMS queue template.
	 */
	@Primary
	@Bean(name = { "jmsTemplate", "transactedJmsTemplate", "jmsQueueTemplate", "transactedJmsQueueTemplate" })
	public JmsTemplate createTransactedJmsQueueTemplate() {
		// Creates the JMS template.
		final JmsTemplate jmsTemplate = new JmsTemplate(this.connectionFactory);
		// If there is an available destination resolver.
		if (this.destinationResolver != null) {
			// Sets the JMS template destination resolver.
			jmsTemplate.setDestinationResolver(this.destinationResolver);
		}
		// Sets the JMS template as transacted.
		jmsTemplate.setSessionTransacted(true);
		// Returns the configured JMS template.
		return jmsTemplate;
	}

	/**
	 * Creates a transacted JMS topic template.
	 *
	 * @return Transacted JMS topic template.
	 */
	@Bean(name = { "jmsTopicTemplate", "transactedJmsTopicTemplate" })
	@ConditionalOnProperty(name = { "spring.jms.pub-sub-domain" }, havingValue = "true")
	public JmsTemplate createTransactedJmsTopicTemplate() {
		// Creates the JMS template.
		final JmsTemplate jmsTemplate = this.createTransactedJmsQueueTemplate();
		// Sets the template for publisher/subscriber.
		jmsTemplate.setPubSubDomain(true);
		// Returns the configured JMS template.
		return jmsTemplate;
	}

}
