package org.coldis.library.spring.health;

import org.coldis.library.helper.DateTimeHelper;
import org.coldis.library.persistence.keyvalue.KeyValue;
import org.coldis.library.persistence.keyvalue.KeyValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check service.
 */
@RestController
@RequestMapping(path = "${org.coldis.configuration.health-check}")
@ConditionalOnProperty(name = "org.coldis.configuration.health-check-enabled", havingValue = "true",
matchIfMissing = true)
public class HealthCheckService {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckService.class);

	/**
	 * Health check key.
	 */
	public static final String HEALTH_CHECK_KEY = "health-check";

	/**
	 * Health check repository.
	 */
	@Autowired(required = false)
	private KeyValueRepository<HealthCheckValue> healthCheckRepository;

	/**
	 * Health check service.
	 *
	 * @return The health check value.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Long check() {
		HealthCheckService.LOGGER.debug("Health check starting.");
		final long initMillis = System.currentTimeMillis();
		try {
			// The health check value.
			Long checkValue = HealthCheckValue.VALUE;
			// If the repository is available.
			if (this.healthCheckRepository != null) {
				// Updates the health check.
				checkValue = this.updateCheckEntity();
			}
			// Returns the entity id.
			return checkValue;
		}
		// If the health check does not succeed.
		catch (final Throwable throwable) {
			// Re-throws the error.
			HealthCheckService.LOGGER.error("Health check failed", throwable);
			throw throwable;
		}
		// At the end.
		finally {
			// Logs the successful health check.
			final long executionTime = System.currentTimeMillis() - initMillis;
			HealthCheckService.LOGGER
			.debug("Health check successfully finished within '" + executionTime + "' milliseconds.");
		}
	}

	/**
	 * Updates the health check.
	 *
	 * @return The health check value.
	 */
	@Transactional
	public Long updateCheckEntity() {
		Long checkValue;
		// Gets the health check object.
		KeyValue<HealthCheckValue> healthCheck = this.healthCheckRepository
				.findById(HealthCheckService.HEALTH_CHECK_KEY).orElse(null);
		// If the health check does not exist yet.
		if (healthCheck == null) {
			// Creates a new health check.
			healthCheck = new KeyValue<>(HealthCheckService.HEALTH_CHECK_KEY, new HealthCheckValue());
		}
		// Saves the health check again.
		healthCheck.setUpdatedAt(DateTimeHelper.getCurrentLocalDateTime());
		checkValue = this.healthCheckRepository.save(healthCheck).getValue().getValue();
		return checkValue;
	}

}
