package org.springframework.boot.autoconfigure.jms.artemis;

import org.springframework.beans.factory.ListableBeanFactory;

/**
 * Extendible Artemis connection factory.
 */
public class ExtensibleArtemisConnectionFactoryFactory extends ArtemisConnectionFactoryFactory {

	/**
	 * Default constructor.
	 *
	 * @param beanFactory Bean factory.
	 * @param properties  Properties.
	 */
	public ExtensibleArtemisConnectionFactoryFactory(final ListableBeanFactory beanFactory, final ArtemisProperties properties) {
		super(beanFactory, properties);
	}
}
