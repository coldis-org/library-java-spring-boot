package org.coldis.library.spring.jms;

import java.util.Arrays;
import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.coldis.library.dto.DtoType;
import org.coldis.library.serialization.ObjectMapperHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Default message converter.
 */
@Component
@Qualifier("dtoJmsMessageConverter")
@ConditionalOnClass(value = Message.class)
@ConditionalOnProperty(
		name = "org.coldis.configuration.jms-message-converter-dto-enabled",
		havingValue = "true",
		matchIfMissing = false
)
public class DtoJmsMessageConverter extends SimpleMessageConverter {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DtoJmsMessageConverter.class);

	/**
	 * DTO type parameter.
	 */
	private static final String DTO_TYPE_PARAMETER = "dtoType";

	/**
	 * Object mapper.
	 */
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * @see org.springframework.jms.support.converter.SimpleMessageConverter#fromMessage(javax.jms.Message)
	 */
	@Override
	public Object fromMessage(
			final Message message) throws JMSException, MessageConversionException {
		// Object.
		Object object = null;
		// If the target class is a DTO.
		final String dtoTypeName = message.getStringProperty(DtoJmsMessageConverter.DTO_TYPE_PARAMETER);
		if (!StringUtils.isEmpty(dtoTypeName)) {
			// Tries to convert the JSON object.
			try {
				final Class<?> dtoType = Class.forName(dtoTypeName);
				object = ObjectMapperHelper.deserialize(this.objectMapper, ((TextMessage) message).getText(), dtoType, false);
			}
			// If the object cannot be converted from JSON.
			catch (final Exception exception) {
				// Logs it.
				DtoJmsMessageConverter.LOGGER.error("Object could not be converted from JSON: ", exception.getLocalizedMessage());
				DtoJmsMessageConverter.LOGGER.debug("Object could not be converted from JSON.", exception);
			}
		}
		// If the object could not be converted from JSON.
		if (object == null) {
			// Tries using default converter.
			object = super.fromMessage(message);
		}
		// Returns the object.
		return object;
	}

	/**
	 * @see org.springframework.jms.support.converter.SimpleMessageConverter#toMessage(java.lang.Object,
	 *      javax.jms.Session)
	 */
	@Override
	public Message toMessage(
			final Object payload,
			final Session session) throws JMSException, MessageConversionException {
		// Message.
		Message message = null;
		// If the payload is a typed object.
		final Optional<DtoType> javaDto = Arrays.stream(payload.getClass().getAnnotationsByType(DtoType.class))
				.filter(dto -> "java".equals(dto.fileExtension())).findFirst();
		if ((payload != null) && javaDto.isPresent()) {
			// Tries to create a message.
			try {
				// Serializes the payload. // TODO Add views.
				final String actualPayload = ObjectMapperHelper.serialize(this.objectMapper, payload, null, false);
				// Creates a new text message.
				message = session.createTextMessage(actualPayload);
				message.setStringProperty(DtoJmsMessageConverter.DTO_TYPE_PARAMETER,
						javaDto.get().namespace() + "." + (javaDto.get().name().isEmpty() ? payload.getClass().getSimpleName() + "Dto" : javaDto.get().name()));
			}
			// If the object cannot be converted from JSON.
			catch (final Exception exception) {
				// Logs it.
				DtoJmsMessageConverter.LOGGER.error("Object could not be serialized to JSON: ", exception.getLocalizedMessage());
				DtoJmsMessageConverter.LOGGER.debug("Object could not be serialized to JSON.", exception);
			}
		}
		// If there is no message yet.
		if (message == null) {
			// Creates a default message.
			message = super.toMessage(payload, session);
		}
		// Returns the message.
		return message;

	}

}
