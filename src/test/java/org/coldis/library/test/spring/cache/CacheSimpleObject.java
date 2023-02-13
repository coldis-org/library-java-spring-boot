package org.coldis.library.test.spring.cache;

/**
 * Object.
 */
public class CacheSimpleObject {

	/**
	 * Attribute.
	 */
	private Integer attribute;

	/**
	 * Gets the attribute.
	 *
	 * @return The attribute.
	 */
	public Integer getAttribute() {
		return this.attribute;
	}

	/**
	 * Sets the attribute.
	 *
	 * @param attribute New attribute.
	 */
	public void setAttribute(
			final Integer attribute) {
		this.attribute = attribute;
	}

	/**
	 * Constructor.
	 */
	public CacheSimpleObject() {
	}

	/**
	 * Constructor.
	 *
	 * @param attribute Attribute.
	 */
	public CacheSimpleObject(final Integer attribute) {
		super();
		this.attribute = attribute;
	}

}
