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

import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationChangeResponse;

/**
 * Configuration manager service interface.
 */
public interface ConfigurationManager {

    /**
     * This API is used to get the configuration by configuration name
     *
     * @param name Name id of the configuration.
     * @return 200 OK with configuration element.
     * @throws Configuration Management Exception.
     */
    Configuration getConfiguration(String name) throws ConfigurationManagementException;

    /**
     * This API is used to delete the configuration by configuration name
     *
     * @param name Name  id of the configuration.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    void deleteConfiguration(String name) throws ConfigurationManagementException;

    /**
     * This API is used to add the given configuration.
     *
     * @param name          Name id of the existing {@link Configuration}.
     * @param configuration {@link Configuration} to be added.
     * @return 201 created. Returns configuration change response with configuration name, tenant domain and change state.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    ConfigurationChangeResponse addConfiguration(String name, Configuration configuration)
            throws ConfigurationManagementException;

    /**
     * This API is used to replace the existing configuration with the given configuration or add the given
     * configuration if an existing configuration is not available.
     *
     * @param name          Name id of the existing {@link Configuration}.
     * @param configuration New {@link Configuration} to replace the existing {@link Configuration}.
     * @return 201 created. Returns configuration change response with configuration name, tenant domain and change state.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    ConfigurationChangeResponse replaceConfiguration(String name, Configuration configuration)
            throws ConfigurationManagementException;

    /**
     * This API is used to update the existing configuration with the given configuration.
     *
     * @param name          Name id of the existing {@link Configuration}.
     * @param configuration New {@link Configuration} to update the existing {@link Configuration}.
     * @return 201 created. Returns configuration change response with configuration name, tenant domain and change state.
     * @throws ConfigurationManagementException Configuration management exception.
     */
    ConfigurationChangeResponse updateConfiguration(String name, Configuration configuration)
            throws ConfigurationManagementException;
}
