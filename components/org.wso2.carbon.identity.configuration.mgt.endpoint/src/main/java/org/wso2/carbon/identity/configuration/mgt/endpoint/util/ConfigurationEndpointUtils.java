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

package org.wso2.carbon.identity.configuration.mgt.endpoint.util;

import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;

import java.util.stream.Collectors;

/**
 * Utility functions required for configuration endpoint
 */
public class ConfigurationEndpointUtils {

    public static ConfigurationManager getConfigurationManager() {

        return (ConfigurationManager) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(ConfigurationManager.class, null);
    }

    public static ConfigurationDTO getConfigurationDTO(Configuration configuration) {

        ConfigurationDTO configurationDTO = new ConfigurationDTO();
        configurationDTO.setName(configuration.getName());
        configurationDTO.setAttributes(
                configuration.getAttributes().stream().map(
                        ConfigurationEndpointUtils::getAttributeDTO
                ).collect(Collectors.toList())
        );
        return configurationDTO;
    }

    public static AttributeDTO getAttributeDTO(Attribute attribute) {

        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setKey(attribute.getKey());
        attributeDTO.setValue(attribute.getValue());
        return attributeDTO;
    }
}
