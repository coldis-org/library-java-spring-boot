package org.coldis.library.spring.exception;

import javax.validation.ConstraintViolationException;

import org.coldis.library.exception.BusinessException;
import org.coldis.library.exception.IntegrationException;
import org.coldis.library.model.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

/**
 * Logs business errors.
 */
public class BusinessExceptionHandler implements ErrorHandler {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionHandler.class);

	/**
	 * @see org.springframework.util.ErrorHandler#handleError(java.lang.Throwable)
	 */
	@Override
	public void handleError(
			final Throwable throwable) {
		// Logs business errors.
		if ((throwable instanceof BusinessException) || (throwable instanceof ConstraintViolationException)) {
			BusinessExceptionHandler.LOGGER.warn("Business exception thrown and handled: " + throwable.getLocalizedMessage());
			BusinessExceptionHandler.LOGGER.debug("Business exception thrown and handled." + throwable);
		}
		// Re-throws any other error.
		else {
			throw new IntegrationException(new SimpleMessage(throwable.getLocalizedMessage()), throwable);
		}

	}

}
