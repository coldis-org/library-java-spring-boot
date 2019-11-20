package org.coldis.library.spring.jms;

import javax.jms.Message;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

/**
 * Default JMS message converter.
 */
@Primary
@Component
@ConditionalOnClass(value = Message.class)
@ConditionalOnProperty(name = "org.coldis.configuration.jms-message-converter-default-enabled", havingValue = "true",
matchIfMissing = false)
public class DefaultJmsMessageConverter extends SimpleMessageConverter {


}
