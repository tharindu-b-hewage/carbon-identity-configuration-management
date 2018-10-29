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
     * Get priority value for the {@link ConfigurationDAO}.
     *
     * @return Priority value for the DAO.
     */
    int getPriority();

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

    /**
     * Add given {@link Configuration}.
     *
     * @param name Name id of the {@link Configuration}
     * @param configuration {@link Configuration} to be added.
     * @return Name id of the added {@link Configuration}.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    String addConfiguration(String name, Configuration configuration) throws ConfigurationManagementException;

    /**
     * Replace given {@link Configuration} or add if existing {@link Configuration} is not present.
     *
     * @param name Name id of the {@link Configuration}
     * @param configuration New {@link Configuration} to replace the existing.
     * @return Name id of the added {@link Configuration}.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    String replaceConfiguration(String name, Configuration configuration) throws ConfigurationManagementException;

    /**
     * Update given {@link Configuration}.
     *
     * @param name Name id of the {@link Configuration}
     * @param configuration New {@link Configuration} to update the existing.
     * @return Name id of the added {@link Configuration}.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    String updateConfiguration(String name, Configuration configuration) throws ConfigurationManagementException;
}
