package org.coldis.library.spring.health;

import org.coldis.library.model.ModelView;
import org.coldis.library.model.TypedObject;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Health check value.
 */
@JsonTypeName(value = HealthCheckValue.TYPE_NAME)
public class HealthCheckValue implements TypedObject {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -5168044828758231509L;

	/**
	 * Type name.
	 */
	public static final String TYPE_NAME = "org.coldis.library.spring.health.HealthCheckValue";

	/**
	 * Fixed value.
	 */
	public static final Long VALUE = 1L;

	/**
	 * Gets the value.
	 *
	 * @return The value.
	 */
	@JsonView({ ModelView.Persistent.class, ModelView.Public.class })
	public Long getValue() {
		return HealthCheckValue.VALUE;
	}

	/**
	 * @see org.coldis.library.model.TypedObject#getTypeName()
	 */
	@Override
	@JsonView({ ModelView.Persistent.class, ModelView.Public.class })
	public String getTypeName() {
		return HealthCheckValue.TYPE_NAME;
	}

}
