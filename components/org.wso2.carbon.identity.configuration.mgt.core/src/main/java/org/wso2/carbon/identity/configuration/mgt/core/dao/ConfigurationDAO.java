package org.wso2.carbon.identity.configuration.mgt.core.dao;

import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;

/**
 * Perform CRUD operations for {@link Configuration}.
 *
 * @since 1.0.0
 */
public interface ConfigurationDAO {

    /**
     * Returns {@link Configuration} by name;
     *
     * @param name Name id of the {@link Configuration} to retrieve.
     * @return {@link Configuration} for the given name.
     */
    Configuration getConfiguration(String name) throws ConfigurationManagementException;

    /**
     * Delete {@link Configuration} by the given name.
     *
     * @param name Name id of the {@link Configuration} to delete.
     * @return Name id of the deleted {@link Configuration}.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    String deleteConfiguration(String name) throws ConfigurationManagementException;
}
