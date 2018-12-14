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
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages;
import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationManagerConfigurationHolder;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils;

import java.util.List;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_ATTRIBUTE_IDENTIFIERS_REQUIRED;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_GET_DAO;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_QUERY_LENGTH_EXCEEDED;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_ADD_REQUEST_INVALID;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_ALREADY_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_DELETE_REQUEST_REQUIRED;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_DOES_NOT_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_GET_REQUEST_INVALID;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_NAME_REQUIRED;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_SEARCH_REQUEST_INVALID;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.SQLConstants.MAX_QUERY_LENGTH_SQL;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.generateUniqueID;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.handleClientException;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils.handleServerException;
// TODO: 12/12/18 Implement file upload and download endpoints
// TODO: 12/12/18 '/id' endpoint implementation for every call to support call by id

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
    public Resources getTenantResources(String searchExpressionSQL) throws ConfigurationManagementException {

        validateSearchRequest(searchExpressionSQL);
        Resources resources = getConfigurationDAO().getTenantResources(searchExpressionSQL);
        if (resources == null) {
            if (log.isDebugEnabled()) {
                log.debug("No resources found for the search.");
            }
            throw ConfigurationUtils.handleClientException(ErrorMessages.ERROR_CODE_RESOURCES_DOES_NOT_EXISTS, null);
        }
        return resources;
    }

    private void validateSearchRequest(String searchExpressionSQL) throws ConfigurationManagementClientException {

        if (StringUtils.isEmpty(searchExpressionSQL)) {
            if (log.isDebugEnabled()) {
                log.debug("Search filter expression: " + searchExpressionSQL + " is not valid");
            }
            throw handleClientException(ERROR_CODE_SEARCH_REQUEST_INVALID, null);
        }

        if (searchExpressionSQL.length() > MAX_QUERY_LENGTH_SQL) {
            if (log.isDebugEnabled()) {
                log.debug("Error building SQL query for the search. Search expression " +
                        "query length: " + searchExpressionSQL.length() + " exceeds the maximum limit: " +
                        MAX_QUERY_LENGTH_SQL);
            }
            throw ConfigurationUtils.handleClientException(ERROR_CODE_QUERY_LENGTH_EXCEEDED, null);
        }
    }

    public Resources getResources() throws ConfigurationManagementException {

        return null;
    }

    public Resources getResourcesByType(String resourceType) throws ConfigurationManagementException {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResource(String resourceTypeName, String resourceName)
            throws ConfigurationManagementException {

        validateResourceRetrieveRequest(resourceTypeName, resourceName);
        ResourceType resourceType = getResourceType(resourceTypeName);
        Resource resource = this.getConfigurationDAO().getResource(getTenantId(), resourceType.getId(), resourceName);
        if (resource == null) {
            if (log.isDebugEnabled()) {
                log.debug("No resource found for the resourceName: " + resourceName);
            }
            throw ConfigurationUtils.handleClientException(
                    ErrorMessages.ERROR_CODE_RESOURCE_DOES_NOT_EXISTS, resourceName, null);
        }
        return resource;
    }

    private void validateResourceRetrieveRequest(String resourceTypeName, String resourceName) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceName) || StringUtils.isEmpty(resourceTypeName)) {
            if (log.isDebugEnabled()) {
                log.debug("Invalid resource identifier with resourceName: " + resourceName + " and resourceTypeName: " + resourceTypeName + ".");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_GET_REQUEST_INVALID, null);
        }
    }

    private int getTenantId() {

        return PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
    }

    private String getTenantDomain() {

        return PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantDomain();
    }

    /**
     * {@inheritDoc}
     */
    public void deleteResource(String resourceTypeName, String resourceName) throws ConfigurationManagementException {

        validateResourceDeleteRequest(resourceTypeName, resourceName);
        this.getConfigurationDAO().deleteResource(resourceTypeName, resourceName);
        if (log.isDebugEnabled()) {
            log.debug(StringUtils.capitalize(resourceName) + " configuration deleted successfully.");
        }
    }

    private void validateResourceDeleteRequest(String resourceTypeName, String resourceName)
            throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceName) || StringUtils.isEmpty(resourceTypeName)) {
            if (log.isDebugEnabled()) {
                log.debug("Error identifying the resource with resource name: " + resourceName + " and resource type:"
                        + resourceTypeName + ".");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_DELETE_REQUEST_REQUIRED, null);
        }

        if (!isResourceExists(resourceTypeName, resourceName)) {
            if (log.isDebugEnabled()) {
                log.debug("A resource with the name: " + resourceName + " does not exists.");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_DOES_NOT_EXISTS, resourceName);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Resource addResource(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle conflict error
        validateResourceCreateRequest(resourceTypeName, resourceAdd);
        Resource resource = generateResourceFromRequest(resourceTypeName, resourceAdd);
        String resourceId = generateUniqueID();
        if (log.isDebugEnabled()) {
            log.debug("Resource id generated: " + resourceId);
        }
        resource.setResourceId(resourceId);
        this.getConfigurationDAO().addResource(resource);
        if (log.isDebugEnabled()) {
            log.debug(resourceAdd.getName() + " resource created successfully.");
        }
        return resource;
    }

    private Resource generateResourceFromRequest(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException {

        Resource resource = new Resource();
        resource.setTenantDomain(getTenantDomain());
        resource.setResourceName(resourceAdd.getName());
        resource.setResourceType(resourceTypeName);
        resource.setAttributes(resourceAdd.getAttributes());
        resource.setLastModified(java.time.Instant.now().toString());
        return resource;
    }

    private void validateResourceCreateRequest(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceTypeName) || !isResourceAddParameterValid(resourceAdd)) {
            throw handleClientException(ERROR_CODE_RESOURCE_ADD_REQUEST_INVALID, null);
        }

        if (isResourceExists(resourceTypeName, resourceAdd.getName())) {
            throw handleClientException(ERROR_CODE_RESOURCE_ALREADY_EXISTS, resourceAdd.getName());
        }
    }

    private boolean isResourceAddParameterValid(ResourceAdd resourceAdd) {

        if (StringUtils.isEmpty(resourceAdd.getName())) {
            if (log.isDebugEnabled()) {
                log.debug("Resource name: " + resourceAdd.getName() + " is not valid.");
            }
            return false;
        }
        // TODO: 12/11/18 Changing to file upload endpoint: Since this is the POST validation, files are always null for the incoming request in the DB.
        //        // Invalid if both attributes and file fields are empty.
        //        if ((resourceAdd.getAttributes() == null || resourceAdd.getAttributes().size() == 0) &&
        //                (resourceAdd.getFile() == null || resourceAdd.getFile().getValue() == null)) {
        //            if (log.isDebugEnabled()) {
        //                log.debug("A resource must contain either an attributes or a file.");
        //            }
        //            return false;
        //        }
        return true;
    }

    private boolean isResourceExists(String resourceTypeName, String resourceName) throws ConfigurationManagementException {

        try {
            getResource(resourceTypeName, resourceName);
            return true;
        } catch (ConfigurationManagementClientException e) {
            if (e.getErrorCode().equals(ERROR_CODE_RESOURCE_DOES_NOT_EXISTS.getCode())) {
                return false;
            }
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Resource replaceResource(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException {

//        this.getConfigurationDAO().replaceResource(name, resource);
//        if (log.isDebugEnabled()) {
//            log.debug(name + " resource replaced successfully.");
//        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Resource updateResource(String resourceTypeName, ResourceAdd resourceAdd) throws ConfigurationManagementException {

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
    public ResourceType getResourceType(String ResourceName) throws ConfigurationManagementException {

        validateResourceTypeRetrieveRequest(ResourceName);
        ResourceType resourceType = getConfigurationDAO().getResourceTypeByName(ResourceName);
        if (resourceType == null || resourceType.getId() == null) {
            if (log.isDebugEnabled()) {
                log.debug("Resource Type: " + resourceType.getName() + " does not exists.");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS, ResourceName);
        }

        if (log.isDebugEnabled()) {
            log.debug("Resource type: " + resourceType.getName() + " retrieved successfully.");
        }
        return resourceType;
    }

    private ResourceType getResourceTypeByIdentifier(String resourceName, String id) throws ConfigurationManagementException {

        return StringUtils.isEmpty(id) ? getConfigurationDAO().getResourceTypeByName(resourceName) :
                getConfigurationDAO().getResourceTypeById(id);
    }

    private void validateResourceTypeRetrieveRequest(String resourceName) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceName)) {
            if (log.isDebugEnabled()) {
                log.debug("Invalid resource type resourceName: " + resourceName + ".");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_REQUIRED, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteResourceType(String resourceName) throws ConfigurationManagementException {

        validateResourceTypeDeleteRequest(resourceName);
        getConfigurationDAO().deleteResourceTypeByName(resourceName);

        if (log.isDebugEnabled()) {
            log.debug("Resource type: " + resourceName + " is successfully deleted.");
        }
    }

    private void validateResourceTypeDeleteRequest(String resourceName) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceName)) {
            if (log.isDebugEnabled()) {
                log.debug("Invalid resource type resourceName: " + resourceName + ".");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_REQUIRED, resourceName, null);
        }

        if (!isResourceTypeExists(resourceName)) {
            if (log.isDebugEnabled()) {
                log.debug("A resource type with the resourceName: " + resourceName + " does not exists.");
            }
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS, resourceName);
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
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_REQUIRED, null);
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
            getResourceType(resourceTypeName);
        } catch (ConfigurationManagementClientException e) {
            if (e.getErrorCode().equals(ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS.getCode())) {
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

    private void validateResourceTypeReplaceRequest(String resourceTypeName) throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceTypeName)) {
            throw handleClientException(ERROR_CODE_RESOURCE_TYPE_NAME_REQUIRED, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ResourceType updateResourceType(ResourceTypeAdd resourceTypeCreate) throws ConfigurationManagementException {

        return null;
    }

    public void deleteAttribute(String resourceTypeName, String resourceName, String attribute) throws ConfigurationManagementException {

    }

    public Attribute getAttribute(String resourceTypeName, String resourceName, String attributeKey) throws ConfigurationManagementException {

        return null;
    }

    public Attribute updateAttribute(String resourceTypeName, String resourceName, Attribute attribute) throws ConfigurationManagementException {

        return null;
    }

    public Attribute addAttribute(String resourceTypeName, String resourceName, Attribute attribute) throws ConfigurationManagementException {

        return null;
    }

    public Attribute replaceAttribute(String resourceTypeName, String resourceName, Attribute attribute)
            throws ConfigurationManagementException {

        validateAttributeReplaceRequest(resourceTypeName, resourceName, attribute);

        String attributeId = generateUniqueID();
        if (log.isDebugEnabled()) {
            log.debug("Attribute id: " + attributeId + " generated in the replace request for possible " +
                    "attribute creation if not already exists.");
        }
        String resourceId = getResourceId(resourceName, resourceTypeName); // TODO: 12/14/18 HTTP PATCH required. move to update
        getAttribute(resourceTypeName, resourceName, attribute.getKey());
        getConfigurationDAO().replaceAttribute(attributeId, resourceId, attribute);
        if (log.isDebugEnabled()) {
            log.debug("Attribute: " + attribute.getKey() + " successfully replaced with the id: "
                    + attributeId);
        }
        return attribute;
    }

    private String getResourceId(String resourceType, String resourceName) throws ConfigurationManagementException {

        String resourceTypeId = getConfigurationDAO().getResourceTypeByName(resourceType).getId();
        return getConfigurationDAO().getResource(
                PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId(),
                resourceTypeId,
                resourceName
        ).getResourceId();
    }

    private void validateAttributeReplaceRequest(String resourceTypeName, String resourceName, Attribute attribute)
            throws ConfigurationManagementException {

        if (StringUtils.isEmpty(resourceName) || StringUtils.isEmpty(resourceTypeName) || (attribute == null)) {
            String attributeIdentifiers = "resourceName = " + resourceName + ", resourceTypeName = " + resourceTypeName
                    + (attribute == null ? ", attribute = null." : ".");
            throw handleClientException(ERROR_CODE_ATTRIBUTE_IDENTIFIERS_REQUIRED, attributeIdentifiers);
        }
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
