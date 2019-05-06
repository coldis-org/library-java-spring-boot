package org.coldis.spring.properties;

import java.math.RoundingMode;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Number properties.
 */
@Component
@ConfigurationProperties(prefix = "org.coldis.config.number")
public class NumberProperties {

	/**
	 * Persistence scale.
	 */
	private static Integer persistenceScale;

	/**
	 * /** Percentage scale.
	 */
	private static Integer percentageScale;

	/**
	 * Currency scale.
	 */
	private static Integer currencyScale;

	/**
	 * Rounding mode.
	 */
	private static RoundingMode roundingMode;

	/**
	 * Gets the persistence scale.
	 *
	 * @return The persistence scale.
	 */
	public static Integer getPersistenceScale() {
		return NumberProperties.persistenceScale;
	}

	/**
	 * Sets the persistence scale.
	 *
	 * @param persistenceScale New persistence scale.
	 */
	public static void setPersistenceScale(final Integer persistenceScale) {
		NumberProperties.persistenceScale = persistenceScale;
	}

	/**
	 * Gets the percentage scale.
	 *
	 * @return The percentage scale.
	 */
	public static Integer getPercentageScale() {
		return NumberProperties.percentageScale;
	}

	/**
	 * Sets the percentage scale.
	 *
	 * @param percentageScale New percentage scale.
	 */
	public static void setPercentageScale(final Integer percentageScale) {
		NumberProperties.percentageScale = percentageScale;
	}

	/**
	 * Gets the currency scale.
	 *
	 * @return The currency scale.
	 */
	public static Integer getCurrencyScale() {
		return NumberProperties.currencyScale;
	}

	/**
	 * Sets the currency scale.
	 *
	 * @param currencyScale New currency scale.
	 */
	public static void setCurrencyScale(final Integer currencyScale) {
		NumberProperties.currencyScale = currencyScale;
	}

	/**
	 * Gets the rounding mode.
	 *
	 * @return The rounding mode.
	 */
	public static RoundingMode getRoundingMode() {
		return NumberProperties.roundingMode;
	}

	/**
	 * Sets the rounding mode.
	 *
	 * @param roundingMode New rounding mode.
	 */
	public static void setRoundingMode(final RoundingMode roundingMode) {
		NumberProperties.roundingMode = roundingMode;
	}

}
