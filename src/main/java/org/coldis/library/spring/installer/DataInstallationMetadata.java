package org.coldis.library.spring.installer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data installation metadata.
 */
public class DataInstallationMetadata {

	/**
	 * Service operation URL.
	 */
	private String serviceOperationUrl = "";

	/**
	 * Id properties.
	 */
	private String[] idProperties = { "id" };

	/**
	 * Service search properties use strategy.
	 */
	private DataInstallerSearchStrategy idPropertiesStrategy = DataInstallerSearchStrategy.PATH;

	/**
	 * Search properties.
	 */
	private String[] searchProperties = { "id" };

	/**
	 * Service search properties use strategy.
	 */
	private DataInstallerSearchStrategy searchPropertiesStrategy = DataInstallerSearchStrategy.PATH;

	/**
	 * Search operation path.
	 */
	private String searchOperationPath = "";

	/**
	 * If any existent object should not be updated.
	 */
	private Boolean createOnly = false;

	/**
	 * Data to be installed.
	 */
	private List<Map<String, Object>> data;

	/**
	 * Gets the service operation URL.
	 *
	 * @return The service operation URL.
	 */
	public String getServiceOperationUrl() {
		return this.serviceOperationUrl;
	}

	/**
	 * Sets the service operation URL.
	 *
	 * @param serviceOperationUrl New service operation URL.
	 */
	public void setServiceOperationUrl(final String serviceOperationUrl) {
		this.serviceOperationUrl = serviceOperationUrl;
	}

	/**
	 * Gets the id properties.
	 *
	 * @return The id properties.
	 */
	public String[] getIdProperties() {
		return this.idProperties;
	}

	/**
	 * Sets the id properties.
	 *
	 * @param idProperties New id properties.
	 */
	public void setIdProperties(final String[] idProperties) {
		this.idProperties = idProperties;
	}

	/**
	 * Gets the id properties strategy.
	 *
	 * @return The id properties strategy.
	 */
	public DataInstallerSearchStrategy getIdPropertiesStrategy() {
		return this.idPropertiesStrategy;
	}

	/**
	 * Sets the id properties strategy.
	 *
	 * @param idPropertiesStrategy New id properties strategy.
	 */
	public void setIdPropertiesStrategy(final DataInstallerSearchStrategy idPropertiesStrategy) {
		this.idPropertiesStrategy = idPropertiesStrategy;
	}

	/**
	 * Gets the search properties.
	 *
	 * @return The search properties.
	 */
	public String[] getSearchProperties() {
		return this.searchProperties;
	}

	/**
	 * Sets the search properties.
	 *
	 * @param searchProperties New search properties.
	 */
	public void setSearchProperties(final String[] searchProperties) {
		this.searchProperties = searchProperties;
	}

	/**
	 * Gets the search properties strategy.
	 *
	 * @return The search properties strategy.
	 */
	public DataInstallerSearchStrategy getSearchPropertiesStrategy() {
		return this.searchPropertiesStrategy;
	}

	/**
	 * Sets the search properties strategy.
	 *
	 * @param searchPropertiesStrategy New search properties strategy.
	 */
	public void setSearchPropertiesStrategy(final DataInstallerSearchStrategy searchPropertiesStrategy) {
		this.searchPropertiesStrategy = searchPropertiesStrategy;
	}

	/**
	 * Gets the search operation path.
	 *
	 * @return The search operation path.
	 */
	public String getSearchOperationPath() {
		return this.searchOperationPath;
	}

	/**
	 * Sets the search operation path.
	 *
	 * @param searchOperationPath New search operation path.
	 */
	public void setSearchOperationPath(final String searchOperationPath) {
		this.searchOperationPath = searchOperationPath;
	}

	/**
	 * Gets the data.
	 *
	 * @return The data.
	 */
	public List<Map<String, Object>> getData() {
		// Makes sure the list is initialized.
		this.data = this.data == null ? new ArrayList<>() : this.data;
		// Returns the list.
		return this.data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data New data.
	 */
	public void setData(final List<Map<String, Object>> data) {
		this.data = data;
	}

	/**
	 * Gets the createOnly.
	 *
	 * @return The createOnly.
	 */
	public Boolean getCreateOnly() {
		return this.createOnly;
	}

	/**
	 * Sets the createOnly.
	 *
	 * @param createOnly New createOnly.
	 */
	public void setCreateOnly(final Boolean createOnly) {
		this.createOnly = createOnly;
	}

}
