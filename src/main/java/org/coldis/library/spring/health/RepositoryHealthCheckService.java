package org.coldis.library.spring.health;

import org.coldis.library.helper.DateTimeHelper;
import org.coldis.library.model.Typable;
import org.coldis.library.persistence.keyvalue.KeyValue;
import org.coldis.library.persistence.keyvalue.KeyValueRepository;
import org.coldis.library.persistence.keyvalue.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	private KeyValueService keyValuService;

	/**
	 * Touches the health check repository.
	 *
	 * @return The health check value.
	 */
	@Transactional(
			propagation = Propagation.REQUIRED,
			timeout = 3
	)
	public HealthCheckValue touch() {
		// Gets the health check object.
		final KeyValue<Typable> healthCheck = this.keyValuService.lock(HealthCheckService.HEALTH_CHECK_KEY).get();
		// Saves the health check again.
		healthCheck.setValue(new HealthCheckValue());
		healthCheck.setUpdatedAt(DateTimeHelper.getCurrentLocalDateTime());
		return (HealthCheckValue) healthCheck.getValue();
	}

}
