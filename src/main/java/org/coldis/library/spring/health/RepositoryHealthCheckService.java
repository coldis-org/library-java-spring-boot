package org.coldis.library.spring.health;

import org.coldis.library.exception.BusinessException;
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
	private KeyValueService keyValueService;

	/**
	 * Touches the health check repository.
	 *
	 * @return                   The health check value.
	 * @throws BusinessException If the health check does not work.
	 */
	@Transactional(
			propagation = Propagation.REQUIRED,
			timeout = 5
	)
	public HealthCheckValue touch() throws BusinessException {
		return (HealthCheckValue) this.keyValueService.lock(HealthCheckService.HEALTH_CHECK_KEY).get().getValue();
	}

}
