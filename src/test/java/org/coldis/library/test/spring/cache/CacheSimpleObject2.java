package org.coldis.library.test.spring.cache;

import java.math.BigDecimal;

import org.coldis.library.model.view.ModelView;
import org.coldis.library.serialization.json.NumberDeserializer;
import org.coldis.library.serialization.json.NumberSerializer;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Object.
 */
public class CacheSimpleObject2 {

	/**
	 * Attribute.
	 */
	private BigDecimal attribute;

	/**
	 * Gets the attribute.
	 *
	 * @return The attribute.
	 */
	@JsonSerialize(using = NumberSerializer.class)
	@JsonDeserialize(using = NumberDeserializer.class)
	@JsonView({ ModelView.Public.class })
	public BigDecimal getAttribute() {
		return this.attribute;
	}

	/**
	 * Sets the attribute.
	 *
	 * @param attribute New attribute.
	 */
	public void setAttribute(
			final BigDecimal attribute) {
		this.attribute = attribute;
	}

	/**
	 * Constructor.
	 */
	public CacheSimpleObject2() {
	}

	/**
	 * Constructor.
	 *
	 * @param attribute Attribute.
	 */
	public CacheSimpleObject2(final BigDecimal attribute) {
		super();
		this.attribute = attribute;
	}

}
