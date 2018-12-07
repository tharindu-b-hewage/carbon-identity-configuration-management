/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.configuration.mgt.core;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages;
import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.AttributeValue;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationManagerConfigurationHolder;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeAddResponse;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeCreate;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils;

import java.util.List;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_GET_DAO;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_INVALID_RESOURCE_TYPE_IDENTIFIER;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_NAME_MISSING;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.generateUniqueID;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.handleClientException;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.handleServerException;

/**
 * Resource Manager service implementation.
 */
public class ConfigurationManagerImpl implements ConfigurationManager {

    private static final Log log = LogFactory.getLog(ConfigurationManagerImpl.class);
    private ConfigurationManagerConfigurationHolder configurationManagerConfigurationHolder;
    private List<ConfigurationDAO> configurationDAOS;
    private static final String CONFIGURATION_DAO = "configurationDAOs"; // TODO: 10/29/18 Why dont we move this to constants

    public ConfigurationManagerImpl(ConfigurationManagerConfigurationHolder configurationManagerConfigurationHolder) {

        this.configurationDAOS = configurationManagerConfigurationHolder.getConfigurationDAOS();
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResource() throws ConfigurationManagementException {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResource(String name, String resourceType)
            throws ConfigurationManagementException {

        Resource resource = this.getConfigurationDAO().getConfiguration(name);
        if (resource == null) {
            if (log.isDebugEnabled()) {
                log.debug("No resource found for the name " + name);
            }
            throw ConfigurationUtils.handleClientException(ErrorMessages.ERROR_CODE_CONFIGURATION_NAME_INVALID, null);
        }
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteResource(String name, String resourceType) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle the record not found error
        this.getConfigurationDAO().deleteConfiguration(name);
        if (log.isDebugEnabled()) {
            log.debug(StringUtils.capitalize(name) + " configuration deleted successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addResource(String name, String resourceType, Resource resource) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle conflict error
        this.getConfigurationDAO().addConfiguration(name, resource);
        if (log.isDebugEnabled()) {
            log.debug(name + " resource created successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void replaceResource(String name, String resourceType, Resource resource) throws ConfigurationManagementException {

        this.getConfigurationDAO().replaceConfiguration(name, resource);
        if (log.isDebugEnabled()) {
            log.debug(name + " resource replaced successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void updateResource(String name, String resourceType, Resource resource) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle the record not found error
        this.getConfigurationDAO().updateConfiguration(name, resource);
        if (log.isDebugEnabled()) {
            log.debug(name + " resource replaced successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public ResourceType getResourceType(String name, String id) throws ConfigurationManagementException {

        validateResourceTypeRetrieveRequest(name, id);
        ResourceType resourceType = getResourceTypeByIdentifier(name, id);
        if (log.isDebugEnabled()) {
            String debugLog = getDebugLogForResourceType(resourceType);
            log.debug(debugLog);
        }
        return resourceType;
    }

    private String getDebugLogForResourceType(ResourceType resourceType) {

        return resourceType != null ? ("Resource type: " + resourceType.getName() + " retrieved successfully.") :
                ("Resource Type: " + resourceType.getName() + " does not exists.");
    }

    private ResourceType getResourceTypeByIdentifier(String name, String id) throws ConfigurationManagementException {

        return StringUtils.isEmpty(id) ? getConfigurationDAO().getResourceTypeByName(name) :
                getConfigurationDAO().getResourceTypeById(id);
    }

    private void validateResourceTypeRetrieveRequest(String name, String id) throws ConfigurationManagementException{

        // Only an either of name or id should be present.
        if (StringUtils.isEmpty(name) == StringUtils.isEmpty(id)) {
            throw handleClientException(ERROR_CODE_INVALID_RESOURCE_TYPE_IDENTIFIER,
                    buildResourceTypeIdentifierString(name, id));
        }
    }

    private String buildResourceTypeIdentifierString(String name, String id) {

        return "name: " + name + " and id: " + id;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteResourceType(String name, String id) throws ConfigurationManagementException {

    }

    /**
     * {@inheritDoc}
     */
    public ResourceTypeAddResponse addResourceType(ResourceTypeCreate resourceTypeCreate) throws ConfigurationManagementException {

        validateResourceTypeCreateRequest(resourceTypeCreate);
        String resourceTypeID = generateUniqueID();
        if (log.isDebugEnabled()) {
            log.debug("Resource type id generated: " + resourceTypeID);
        }
        ResourceType resourceType = generateResourceTypeFromRequest(resourceTypeCreate, resourceTypeID);
        getConfigurationDAO().addResourceType(resourceType);

        if (log.isDebugEnabled()) {
            log.debug("Resource type: " + resourceType.getName() + " successfully created with the id: "
                    + resourceType.getId());
        }
        return new ResourceTypeAddResponse(resourceType.getId(), resourceType.getName());
    }

    private void validateResourceTypeCreateRequest(ResourceTypeCreate resourceTypeCreate) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceTypeCreate.getName())) {
            throw handleClientException(ERROR_CODE_RESOURCE_NAME_MISSING, null);
        }

        if (isResourceTypeExists(resourceTypeCreate.getName())) {
            if (log.isDebugEnabled()) {
                log.debug("A resource type with the name: " + resourceTypeCreate.getName() + " already exists.");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS, resourceTypeCreate.getName());
        }
    }

    private boolean isResourceTypeExists(String resourceTypeName) throws ConfigurationManagementException{

        return !StringUtils.isEmpty(getResourceType(resourceTypeName, null).getId());
    }

    private ResourceType generateResourceTypeFromRequest(ResourceTypeCreate resourceTypeCreate, String resourceTypeID) {

        ResourceType resourceType = new ResourceType();
        resourceType.setName(resourceTypeCreate.getName());
        resourceType.setDescription(resourceTypeCreate.getDescription());
        resourceType.setId(resourceTypeID);

        return resourceType;
    }

    /**
     * {@inheritDoc}
     */
    public void replaceResourceType(ResourceTypeCreate resourceTypeCreate) throws ConfigurationManagementException {

    }

    /**
     * {@inheritDoc}
     */
    public void updateResourceType(ResourceTypeCreate resourceTypeCreate) throws ConfigurationManagementException {

    }

    public AttributeValue getAttributeValue(String name, String resourceType, String attribute) throws ConfigurationManagementException {

        return null;
    }

    public void deleteAttribute(String name, String resourceType, String attribute) throws ConfigurationManagementException {

    }

    public void updateAttribute(String name, String resourceType, Attribute attribute) throws ConfigurationManagementException {

    }

    public void createAttribute(String name, String resourceType, Attribute attribute) throws ConfigurationManagementException {

    }

    public void replaceAttribute(String name, String resourceType, Attribute attribute) throws ConfigurationManagementException {

    }

    /**
     * Select highest priority Resource DAO from an already sorted list of Resource DAOs.
     *
     * @return Highest priority Resource DAO.
     */
    private ConfigurationDAO  getConfigurationDAO() throws ConfigurationManagementException {

        if (!this.configurationDAOS.isEmpty()) {
            return configurationDAOS.get(configurationDAOS.size() - 1);
        } else {
            throw handleServerException(ERROR_CODE_GET_DAO, CONFIGURATION_DAO);
        }
    }
}
