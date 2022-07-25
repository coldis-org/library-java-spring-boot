package org.coldis.library.spring.jms;

import org.springframework.jms.core.JmsTemplate;

/**
 * JMS template service.
 */
public interface JmsTemplateHelperService {

	/**
	 * Sends a JMS message.
	 *
	 * @param jmsTemplate         JMS template.
	 * @param destination         Destination.
	 * @param message             Message.
	 * @param messageId           Message id.
	 * @param minimumDelaySeconds Minimum delay (in seconds).
	 * @param maximumDelaySeconds Maximum delay (in seconds).
	 */
	void send(
			JmsTemplate jmsTemplate,
			String destination,
			Object message,
			String messageId,
			Integer minimumDelaySeconds,
			Integer maximumDelaySeconds);

}
