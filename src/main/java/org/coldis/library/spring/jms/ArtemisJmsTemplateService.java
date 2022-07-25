package org.coldis.library.spring.jms;

import java.util.Random;

import org.apache.activemq.artemis.api.core.Message;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * JMS template helper.
 */
@Component
@ConditionalOnClass(value = { JmsTemplate.class, ArtemisProperties.class })
public class ArtemisJmsTemplateService implements JmsTemplateService {

	/**
	 * Random.
	 */
	private static final Random RANDOM = new Random();

	/**
	 * @see org.coldis.library.spring.jms.JmsTemplateService#send(org.springframework.jms.core.JmsTemplate,
	 *      java.lang.String, java.lang.Object, java.lang.String, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public void send(
			final JmsTemplate jmsTemplate,
			final String destination,
			final Object message,
			final String messageId,
			final Integer minimumDelaySeconds,
			final Integer maximumDelaySeconds) {
		// Delay.
		final Integer actualMinimumDelaySeconds = ((minimumDelaySeconds == null) || (minimumDelaySeconds < 0) ? 0 : minimumDelaySeconds);
		final Integer actualMaximumDelaySeconds = ((maximumDelaySeconds == null) || (maximumDelaySeconds < 0) ? 0 : maximumDelaySeconds);
		// Tries sending the message.
		jmsTemplate.send(destination, session -> {
			// Creates the message.
			final javax.jms.Message jmsMessage = jmsTemplate.getMessageConverter().toMessage(message, session);
			if ((actualMinimumDelaySeconds > 0) || (actualMaximumDelaySeconds > 0)) {
				jmsMessage.setLongProperty(Message.HDR_SCHEDULED_DELIVERY_TIME.toString(), System.currentTimeMillis() + (actualMinimumDelaySeconds * 1000)
						+ (actualMaximumDelaySeconds == 0 ? 0 : ArtemisJmsTemplateService.RANDOM.nextInt(actualMaximumDelaySeconds * 1000)));
			}
			// Sets the message id.
			if (messageId != null) {
				jmsMessage.setStringProperty(Message.HDR_LAST_VALUE_NAME.toString(), messageId);
			}
			return jmsMessage;
		});
	}

}
