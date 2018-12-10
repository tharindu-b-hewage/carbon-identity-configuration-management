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
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.AttributePathParameter;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationManagerConfigurationHolder;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.core.model.search.SearchCondition;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils;

import java.util.List;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_GET_DAO;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_NAME_INVALID;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_NAME_MISSING;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.generateUniqueID;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.handleClientException;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.handleServerException;

/**
 * Resource Manager service implementation.
 */
public class ConfigurationManagerImpl implements ConfigurationManager {

    private static final Log log = LogFactory.getLog(ConfigurationManagerImpl.class);
    private List<ConfigurationDAO> configurationDAOS;
    private static final String CONFIGURATION_DAO = "configurationDAOs"; // TODO: 10/29/18 Why dont we move this to constants

    public ConfigurationManagerImpl(ConfigurationManagerConfigurationHolder configurationManagerConfigurationHolder) {

        this.configurationDAOS = configurationManagerConfigurationHolder.getConfigurationDAOS();
    }

    /**
     * {@inheritDoc}
     */
    public Resources getTenantResources(SearchCondition searchCondition) throws ConfigurationManagementException {

        return null;
    }

    public Resources getResources(SearchCondition searchCondition) throws ConfigurationManagementException {

        return null;
    }

    public Resources getResourcesByType(String resourceType, SearchCondition searchCondition) throws ConfigurationManagementException {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResource(String name, String resourceType, SearchCondition searchCondition)
            throws ConfigurationManagementException {

        Resource resource = this.getConfigurationDAO().getResource(name);
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
        this.getConfigurationDAO().deleteResource(name);
        if (log.isDebugEnabled()) {
            log.debug(StringUtils.capitalize(name) + " configuration deleted successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Resource addResource(String resourceType, ResourceAdd resourceAdd) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle conflict error
        this.getConfigurationDAO().addResource(resourceType, resourceAdd);
        if (log.isDebugEnabled()) {
            log.debug(resourceAdd.getName() + " resource created successfully.");
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Resource replaceResource(String resourceType, ResourceAdd resourceAdd) throws ConfigurationManagementException {

//        this.getConfigurationDAO().replaceResource(name, resource);
//        if (log.isDebugEnabled()) {
//            log.debug(name + " resource replaced successfully.");
//        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Resource updateResource(String resourceType, ResourceAdd resourceAdd) throws ConfigurationManagementException {

//        // TODO: 10/29/18 DAO will handle the record not found error
//        this.getConfigurationDAO().updateConfiguration(name, resource);
//        if (log.isDebugEnabled()) {
//            log.debug(name + " resource replaced successfully.");
//        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public ResourceType getResourceType(String name, SearchCondition searchCondition) throws ConfigurationManagementException {

        validateResourceTypeRetrieveRequest(name);
        ResourceType resourceType = getConfigurationDAO().getResourceTypeByName(name);
        if (resourceType == null || resourceType.getId() == null) {
            if (log.isDebugEnabled()) {
                log.debug("Resource Type: " + resourceType.getName() + " does not exists.");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_INVALID, name);
        }

        if (log.isDebugEnabled()) {
            log.debug("Resource type: " + resourceType.getName() + " retrieved successfully.");
        }
        return resourceType;
    }

    private ResourceType getResourceTypeByIdentifier(String name, String id) throws ConfigurationManagementException {

        return StringUtils.isEmpty(id) ? getConfigurationDAO().getResourceTypeByName(name) :
                getConfigurationDAO().getResourceTypeById(id);
    }

    private void validateResourceTypeRetrieveRequest(String name) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(name)) {
            if (log.isDebugEnabled()) {
                log.debug("Invalid resource type name: " + name + ".");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_MISSING, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteResourceType(String name) throws ConfigurationManagementException {

        validateResourceTypeDeleteRequest(name);
        getConfigurationDAO().deleteResourceTypeByName(name);

        if (log.isDebugEnabled()) {
            log.debug("Resource type: " + name + " is successfully deleted.");
        }
    }

    private void validateResourceTypeDeleteRequest(String name) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(name)) {
            if (log.isDebugEnabled()) {
                log.debug("Invalid resource type name: " + name + ".");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_MISSING, null);
        }

        if (!isResourceTypeExists(name)) {
            if (log.isDebugEnabled()) {
                log.debug("A resource type with the name: " + name + " does not exists.");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ResourceType addResourceType(ResourceTypeAdd resourceTypeAdd) throws ConfigurationManagementException {

        validateResourceTypeCreateRequest(resourceTypeAdd);
        String resourceTypeID = generateUniqueID();
        if (log.isDebugEnabled()) {
            log.debug("Resource type id generated: " + resourceTypeID);
        }
        ResourceType resourceType = generateResourceTypeFromRequest(resourceTypeAdd, resourceTypeID);
        getConfigurationDAO().addResourceType(resourceType);

        if (log.isDebugEnabled()) {
            log.debug("Resource type: " + resourceType.getName() + " successfully created with the id: "
                    + resourceType.getId());
        }
        return new ResourceType(
                resourceType.getName(),
                resourceType.getId(),
                resourceType.getDescription()
        );
    }

    private void validateResourceTypeCreateRequest(ResourceTypeAdd resourceTypeAdd) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceTypeAdd.getName())) {
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_MISSING, null);
        }

        if (isResourceTypeExists(resourceTypeAdd.getName())) {
            if (log.isDebugEnabled()) {
                log.debug("A resource type with the name: " + resourceTypeAdd.getName() + " already exists.");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS, resourceTypeAdd.getName());
        }
    }

    private boolean isResourceTypeExists(String resourceTypeName) throws ConfigurationManagementException {

        try {
            getResourceType(resourceTypeName, null);
        } catch (ConfigurationManagementClientException e) {
            if (e.getErrorCode().equals(ERROR_CODE_RESOURCE_TYPE_NAME_INVALID.getCode())) {
                return false;
            } else {
                throw e; // TODO: 12/9/18 For any other exception, throw. Verify this.
            }
        }
        return true;
    }

    private ResourceType generateResourceTypeFromRequest(ResourceTypeAdd resourceTypeCreate, String resourceTypeID) {

        ResourceType resourceType = new ResourceType();
        resourceType.setName(resourceTypeCreate.getName());
        resourceType.setDescription(resourceTypeCreate.getDescription());
        resourceType.setId(resourceTypeID);

        return resourceType;
    }

    /**
     * {@inheritDoc}
     */
    public ResourceType replaceResourceType(ResourceTypeAdd resourceTypeAdd) throws ConfigurationManagementException {

        validateResourceTypeReplaceRequest(resourceTypeAdd.getName());
        String resourceTypeID = generateUniqueID();
        if (log.isDebugEnabled()) {
            log.debug("Resource type id generated: " + resourceTypeID + " for the resource type.");
        }
        ResourceType resourceType = generateResourceTypeFromRequest(resourceTypeAdd, resourceTypeID);
        getConfigurationDAO().replaceResourceType(resourceType);

        if (log.isDebugEnabled()) {
            log.debug("Resource type: " + resourceType.getName() + " successfully replaced with the id: "
                    + resourceType.getId());
        }
        return new ResourceType(
                resourceType.getName(),
                resourceType.getId(),
                resourceType.getDescription()
        );
    }

    private void validateResourceTypeReplaceRequest(String name) throws ConfigurationManagementException{

        if (StringUtils.isEmpty(name)) {
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_MISSING, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ResourceType updateResourceType(ResourceTypeAdd resourceTypeCreate) throws ConfigurationManagementException {

        return null;
    }

    public void deleteAttribute(String name, String resourceType, String attribute) throws ConfigurationManagementException {

    }

    public Attribute getAttribute(AttributePathParameter attributePathParameter, SearchCondition searchCondition) throws ConfigurationManagementException {

        return null;
    }

    public Attribute updateAttribute(String name, String resourceType, Attribute attribute) throws ConfigurationManagementException {

        return null;
    }

    public Attribute addAttribute(String name, String resourceType, Attribute attribute) throws ConfigurationManagementException {

        return null;
    }

    public Attribute replaceAttribute(String name, String resourceType, Attribute attribute) throws ConfigurationManagementException {

        return null;
    }

    /**
     * Select highest priority Resource DAO from an already sorted list of Resource DAOs.
     *
     * @return Highest priority Resource DAO.
     */
    private ConfigurationDAO getConfigurationDAO() throws ConfigurationManagementException {

        if (!this.configurationDAOS.isEmpty()) {
            return configurationDAOS.get(configurationDAOS.size() - 1);
        } else {
            throw handleServerException(ERROR_CODE_GET_DAO, CONFIGURATION_DAO);
        }
    }
}
