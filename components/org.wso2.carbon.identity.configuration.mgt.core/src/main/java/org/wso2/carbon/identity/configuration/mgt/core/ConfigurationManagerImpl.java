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
import org.wso2.carbon.identity.configuration.mgt.core.dao.impl.ConfigurationDAOImpl;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationChangeResponse;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConsentUtils;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants
        .STATE_ADD_CONFIGURATION_CHANGE_RESPONSE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants
        .STATE_REPLACE_CONFIGURATION_CHANGE_RESPONSE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.STATE_UPDATE_CONFIGURATION_CHANGE_RESPONSE;

/**
 * Configuration Manager service implementation.
 */
public class ConfigurationManagerImpl implements ConfigurationManager {

    private static final Log log = LogFactory.getLog(ConfigurationManagerImpl.class);
    private ConfigurationDAO configurationDAO = new ConfigurationDAOImpl();

    public ConfigurationManagerImpl() {

    }

    /**
     * {@inheritDoc}
     */
    public Configuration getConfiguration(String name) throws ConfigurationManagementException {

        Configuration configuration = this.configurationDAO.getConfiguration(name);
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
        this.configurationDAO.deleteConfiguration(name);
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
        this.configurationDAO.addConfiguration(name, configuration);
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

        this.configurationDAO.replaceConfiguration(name, configuration);
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
        this.configurationDAO.updateConfiguration(name, configuration);
        if (log.isDebugEnabled()) {
            log.debug(name + " configuration replaced successfully.");
        }
        return new ConfigurationChangeResponse(
                name,
                PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantDomain(),
                STATE_UPDATE_CONFIGURATION_CHANGE_RESPONSE
        );
    }
}
