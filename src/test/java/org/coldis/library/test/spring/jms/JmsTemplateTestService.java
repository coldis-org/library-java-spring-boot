package org.coldis.library.test.spring.jms;

import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;

import org.coldis.library.spring.jms.JmsTemplateHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test service.
 */
@Component
public class JmsTemplateTestService {

	/**
	 * Test queue.
	 */
	public static final String JMS_TEMPLATE_TEST_QUEUE = "JmsTemplateTestQueue";

	/**
	 * Acknowledge messages.
	 */
	public static Set<String> ACKED_MESSAGES = new HashSet<>();

	/**
	 * JMS template.
	 */
	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * JMS template.
	 */
	@Autowired
	private JmsTemplateHelperService jmsTemplateHelperService;

	/**
	 * Consumes messages.
	 *
	 * @param  destination
	 * @throws JMSException If the message cannot be consumed.
	 */
	@Transactional
	public void consumeMessage(
			final String destination) throws JMSException {
		final Message message = this.jmsTemplate.receive(destination);
		JmsTemplateTestService.ACKED_MESSAGES.add(message.getBody(String.class));
	}

	/**
	 * Sends duplicate messages.
	 *
	 * @param destination
	 * @param message
	 * @param messageId
	 * @param minimumDelaySeconds
	 * @param maximumDelaySeconds
	 */
	@Transactional
	public void sendMessage(
			final String destination,
			final Object message,
			final String messageId,
			final Integer minimumDelaySeconds,
			final Integer maximumDelaySeconds) {
		this.jmsTemplateHelperService.send(this.jmsTemplate, destination, message, messageId, minimumDelaySeconds, maximumDelaySeconds);
	}

}
