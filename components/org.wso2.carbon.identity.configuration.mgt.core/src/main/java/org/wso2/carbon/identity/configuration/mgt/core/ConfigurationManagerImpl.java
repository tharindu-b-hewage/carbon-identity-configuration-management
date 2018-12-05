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
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationManagerConfigurationHolder;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeCreate;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils;

import java.util.List;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_GET_DAO;
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
    public Resource getResource(String name)
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
    public void deleteResource(String name) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle the record not found error
        this.getConfigurationDAO().deleteConfiguration(name);
        if (log.isDebugEnabled()) {
            log.debug(StringUtils.capitalize(name) + " configuration deleted successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addResource(String name, Resource resource) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle conflict error
        this.getConfigurationDAO().addConfiguration(name, resource);
        if (log.isDebugEnabled()) {
            log.debug(name + " resource created successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void replaceResource(String name, Resource resource) throws ConfigurationManagementException {

        this.getConfigurationDAO().replaceConfiguration(name, resource);
        if (log.isDebugEnabled()) {
            log.debug(name + " resource replaced successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void updateResource(String name, Resource resource) throws ConfigurationManagementException {

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

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteResourceType(String name, String id) throws ConfigurationManagementException {

    }

    /**
     * {@inheritDoc}
     */
    public void addResourceType(ResourceTypeCreate resourceTypeCreate) throws ConfigurationManagementException {

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

    ;

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
