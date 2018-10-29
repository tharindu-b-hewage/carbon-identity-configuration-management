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
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationChangeResponse;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationManagerConfigurationHolder;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConsentUtils;

import java.util.List;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_GET_DAO;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.STATE_ADD_CONFIGURATION_CHANGE_RESPONSE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.STATE_REPLACE_CONFIGURATION_CHANGE_RESPONSE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.STATE_UPDATE_CONFIGURATION_CHANGE_RESPONSE;
import static org.wso2.carbon.identity.configuration.mgt.core.util.ConsentUtils.handleServerException;

/**
 * Configuration Manager service implementation.
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
    public Configuration getConfiguration(String name) throws ConfigurationManagementException {

        Configuration configuration = this.getConfigurationDAO().getConfiguration(name);
        if (configuration == null) {
            if (log.isDebugEnabled()) {
                log.debug("No configuration found for the name " + name);
            }
            throw ConsentUtils.handleClientException(ErrorMessages.ERROR_CODE_CONFIGURATION_NAME_INVALID, null);
        }
        return configuration;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteConfiguration(String name) throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle the record not found error
        this.getConfigurationDAO().deleteConfiguration(name);
        if (log.isDebugEnabled()) {
            log.debug(StringUtils.capitalize(name) + " configuration deleted successfully.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public ConfigurationChangeResponse addConfiguration(String name, Configuration configuration)
            throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle conflict error
        this.getConfigurationDAO().addConfiguration(name, configuration);
        if (log.isDebugEnabled()) {
            log.debug(name + " configuration created successfully.");
        }
        return new ConfigurationChangeResponse(
                name,
                PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantDomain(),
                STATE_ADD_CONFIGURATION_CHANGE_RESPONSE
        );
    }

    /**
     * {@inheritDoc}
     */
    public ConfigurationChangeResponse replaceConfiguration(String name, Configuration configuration)
            throws ConfigurationManagementException {

        this.getConfigurationDAO().replaceConfiguration(name, configuration);
        if (log.isDebugEnabled()) {
            log.debug(name + " configuration replaced successfully.");
        }
        return new ConfigurationChangeResponse(
                name,
                PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantDomain(),
                STATE_REPLACE_CONFIGURATION_CHANGE_RESPONSE
        );
    }

    /**
     * {@inheritDoc}
     */
    public ConfigurationChangeResponse updateConfiguration(String name, Configuration configuration)
            throws ConfigurationManagementException {

        // TODO: 10/29/18 DAO will handle the record not found error
        this.getConfigurationDAO().updateConfiguration(name, configuration);
        if (log.isDebugEnabled()) {
            log.debug(name + " configuration replaced successfully.");
        }
        return new ConfigurationChangeResponse(
                name,
                PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantDomain(),
                STATE_UPDATE_CONFIGURATION_CHANGE_RESPONSE
        );
    }

    /**
     * Select highest priority Configuration DAO from an already sorted list of Configuration DAOs.
     *
     * @return Highest priority Configuration DAO.
     */
    private ConfigurationDAO getConfigurationDAO() throws ConfigurationManagementException {

        if (!this.configurationDAOS.isEmpty()) {
            return configurationDAOS.get(configurationDAOS.size() - 1);
        } else {
            throw handleServerException(ERROR_CODE_GET_DAO, CONFIGURATION_DAO);
        }
    }
}
