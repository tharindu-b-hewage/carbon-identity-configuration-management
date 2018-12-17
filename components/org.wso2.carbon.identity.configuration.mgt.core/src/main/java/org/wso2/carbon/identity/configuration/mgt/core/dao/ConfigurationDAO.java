/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.identity.configuration.mgt.core.dao;

import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.core.search.ComplexCondition;
import org.wso2.carbon.identity.configuration.mgt.core.search.Condition;

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

    Resources getTenantResources(Condition condition) throws ConfigurationManagementException;

    /**
     * Returns {@link Resource} by name;
     *
     * @param name Name id of the {@link Resource} to retrieve.
     * @return {@link Resource} for the given name.
     */
    Resource getResource(int tenantId, String resourceTypeId, String name) throws ConfigurationManagementException;

    /**
     * Delete {@link Resource} by the given resourceName.
     *
     * @param resourceName Name id of the {@link Resource} to delete.
     * @return Name id of the deleted {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    void deleteResource(String resourceTypeName, String resourceName) throws ConfigurationManagementException;

    /**
     * Add given {@link Resource}.
     *
     * @param resource {@link Resource} to be added.
     * @return Name id of the added {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    void addResource(Resource resource) throws ConfigurationManagementException;

    /**
     * Replace given {@link Resource} or add if existing {@link Resource} is not present.
     *
     * @param resourceName Name id of the {@link Resource}
     * @param resource     New {@link Resource} to replace the existing.
     * @return Name id of the added {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    void replaceResource(String resourceName, Resource resource) throws ConfigurationManagementException;

    /**
     * Update given {@link Resource}.
     *
     * @param resourceName Name id of the {@link Resource}
     * @param resource     New {@link Resource} to update the existing.
     * @return Name id of the added {@link Resource}.
     * @throws ConfigurationManagementException Resource management exception.
     */
    void updateResource(String resourceName, Resource resource) throws ConfigurationManagementException;

    void addResourceType(ResourceType resourceType) throws ConfigurationManagementException;

    void replaceResourceType(ResourceType resourceType) throws ConfigurationManagementException;

    ResourceType getResourceTypeByName(String resourceTypeName) throws ConfigurationManagementException;

    ResourceType getResourceTypeById(String resourceTypeId) throws ConfigurationManagementException;

    void deleteResourceTypeByName(String resourceTypeName) throws ConfigurationManagementException;

    Attribute getAttribute(String resourceId, String attributeKey) throws ConfigurationManagementException;

    void updateAttribute(String attributeId, Attribute attribute) throws ConfigurationManagementException;

    void deleteAttribute(String attributeId, String attributeKey) throws ConfigurationManagementException;
}
