package org.coldis.library.spring.health;

import org.coldis.library.helper.DateTimeHelper;
import org.coldis.library.persistence.keyvalue.KeyValue;
import org.coldis.library.persistence.keyvalue.KeyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository health check service.
 */
@Service
@ConditionalOnClass(value = KeyValueRepository.class)
public class RepositoryHealthCheckService {

	/**
	 * Health check repository.
	 */
	@Autowired(required = false)
	private KeyValueRepository<HealthCheckValue> healthCheckRepository;

	/**
	 * Touches the health check repository.
	 *
	 * @return The health check value.
	 */
	@Transactional
	public Long touch() {
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
