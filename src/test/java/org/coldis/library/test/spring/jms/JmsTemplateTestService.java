package org.coldis.library.test.spring.jms;

import java.util.HashSet;
import java.util.Set;

import org.coldis.library.spring.jms.JmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
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
	private JmsTemplateService jmsTemplateService;

	/**
	 * Consumes messages.
	 *
	 * @param message Message.
	 */
	@Transactional
	@JmsListener(destination = JmsTemplateTestService.JMS_TEMPLATE_TEST_QUEUE)
	public void consumeMessage(
			final String message) {
		JmsTemplateTestService.ACKED_MESSAGES.add(message);
	}

	/**
	 * Sends duplicate messages.
	 */
	@Transactional
	public void sendMessage(
			final String destination,
			final Object message,
			final String messageId,
			final Integer minimumDelaySeconds,
			final Integer maximumDelaySeconds,
			final Boolean ignoreDuplicateIdError) {
		this.jmsTemplateService.send(this.jmsTemplate, destination, message, messageId, minimumDelaySeconds, maximumDelaySeconds, ignoreDuplicateIdError);
	}

}
