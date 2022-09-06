package org.coldis.library.spring.exception;

import javax.validation.ConstraintViolationException;

import org.coldis.library.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;
import org.springframework.util.ReflectionUtils;

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
		Throwable actualThrowable = throwable;
		boolean businessException = false;
		while (actualThrowable != null) {
			if ((actualThrowable instanceof BusinessException) || (actualThrowable instanceof ConstraintViolationException)) {
				businessException = true;
				break;
			}
			actualThrowable = throwable.getCause();
		}
		if (businessException) {
			BusinessExceptionHandler.LOGGER.warn("Business exception thrown and handled: " + throwable.getLocalizedMessage());
			BusinessExceptionHandler.LOGGER.debug("Business exception thrown and handled." + throwable);
		}
		// Re-throws any other error.
		else {
			ReflectionUtils.rethrowRuntimeException(throwable);
		}

	}

}
