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

package org.wso2.carbon.identity.configuration.mgt.core;

import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.core.search.Condition;

/**
 * Resource manager service interface.
 */
public interface ConfigurationManager {

    // --------------------------------Search---------------------------------------------------------------------

    /**
     * Get resources from all the tenants with optional search parameters;
     *
     * @return
     * @throws ConfigurationManagementException
     */
    Resources getTenantResources(Condition searchCondition) throws ConfigurationManagementException;

    // --------------------------------Resource Type--------------------------------------------------------------
    ResourceType addResourceType(ResourceTypeAdd resourceTypeAdd) throws ConfigurationManagementException;

    ResourceType replaceResourceType(ResourceTypeAdd resourceTypeAdd) throws ConfigurationManagementException;

    ResourceType updateResourceType(ResourceTypeAdd resourceTypeAdd) throws ConfigurationManagementException;

    ResourceType getResourceType(String name)
            throws ConfigurationManagementException;

    void deleteResourceType(String name) throws ConfigurationManagementException;

    // --------------------------------Resources-------------------------------------------------------------------

    /**
     * Get all the resources of the current tenant based with a SearchContext object
     *
     * @return
     * @throws ConfigurationManagementException
     */
    Resources getResources() throws ConfigurationManagementException;

    Resources getResourcesByType(String resourceType)
            throws ConfigurationManagementException;

    // --------------------------------Resource---------------------------------------------------------------------

    /**
     * This API is used to add the given resource.
     *
     * @return 201 created. Returns resource change response with resource name, tenant domain and change state.
     * @throws ConfigurationManagementException Resource management exception.
     */
    Resource addResource(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException;

    /**
     * This API is used to replace the existing resource with the given resource or add the given
     * resource if an existing resource is not available.
     *
     * @return 201 created. Returns resource change response with resource name, tenant domain and change state.
     * @throws ConfigurationManagementException Resource management exception.
     */
    Resource replaceResource(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException;

    /**
     * This API is used to update the existing resource with the given resource.
     *
     * @return 201 created. Returns resource change response with resource name, tenant domain and change state.
     * @throws ConfigurationManagementException Resource management exception.
     */
    Resource updateResource(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException;

    Resource getResource(String resourceTypeName, String resourceName)
            throws ConfigurationManagementException;

    void deleteResource(String resourceTypeName, String resourceName) throws ConfigurationManagementException;

    // -------------------------------- Attribute-------------------------------------------------------------------
    Attribute addAttribute(String resourceTypeName, String resourceName, Attribute attribute)
            throws ConfigurationManagementException;

    Attribute replaceAttribute(String resourceTypeName, String resourceName, Attribute attribute)
            throws ConfigurationManagementException;

    Attribute updateAttribute(String resourceTypeName, String resourceName, Attribute attribute)
            throws ConfigurationManagementException;

    Attribute getAttribute(String resourceTypeName, String resourceName, String attributeKey)
            throws ConfigurationManagementException;

    void deleteAttribute(String resourceTypeName, String resourceName, String attributeKey)
            throws ConfigurationManagementException;
}
