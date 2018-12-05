package org.wso2.carbon.identity.configuration.mgt.core.dao;

import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;

/**
 * Perform CRUD operations for {@link Resource}.
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
     * Returns {@link Resource} by name;
     *
     * @param name Name id of the {@link Resource} to retrieve.
     * @return {@link Resource} for the given name.
     */
    Resource getConfiguration(String name) throws ConfigurationManagementException;

    /**
     * Delete {@link Resource} by the given name.
     *
     * @param name Name id of the {@link Resource} to delete.
     * @return Name id of the deleted {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    String deleteConfiguration(String name) throws ConfigurationManagementException;

    /**
     * Add given {@link Resource}.
     *
     * @param name Name id of the {@link Resource}
     * @param resource {@link Resource} to be added.
     * @return Name id of the added {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    String addConfiguration(String name, Resource resource) throws ConfigurationManagementException;

    /**
     * Replace given {@link Resource} or add if existing {@link Resource} is not present.
     *
     * @param name Name id of the {@link Resource}
     * @param resource New {@link Resource} to replace the existing.
     * @return Name id of the added {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    String replaceConfiguration(String name, Resource resource) throws ConfigurationManagementException;

    /**
     * Update given {@link Resource}.
     *
     * @param name Name id of the {@link Resource}
     * @param resource New {@link Resource} to update the existing.
     * @return Name id of the added {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    String updateConfiguration(String name, Resource resource) throws ConfigurationManagementException;
}
