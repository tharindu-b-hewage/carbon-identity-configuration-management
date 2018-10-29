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

import org.apache.commons.logging.Log;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.BadRequestException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.ConflictRequestException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.ForbiddenException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.InternalServerErrorException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.NotFoundException;

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

    private static AttributeDTO getAttributeDTO(Attribute attribute) {

        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setKey(attribute.getKey());
        attributeDTO.setValue(attribute.getValue());
        return attributeDTO;
    }

    private static ErrorDTO getErrorDTO(String message, String description, String code) {

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(code);
        errorDTO.setMessage(message);
        errorDTO.setDescription(description);
        return errorDTO;
    }

    public static NotFoundException buildNotFoundRequestException(String description, String code,
                                                                  Log log, Throwable e) {

        ErrorDTO errorDTO = getErrorDTO(ConfigurationConstants.STATUS_BAD_REQUEST_MESSAGE_DEFAULT, description, code);
        logDebug(log, e);
        return new NotFoundException(errorDTO);
    }

    /**
     * This method is used to create a BadRequestException with the known errorCode and message.
     *
     * @param description Error Message Desription.
     * @param code        Error Code.
     * @return BadRequestException with the given errorCode and description.
     */
    public static BadRequestException buildBadRequestException(String description, String code,
                                                               Log log, Throwable e) {

        ErrorDTO errorDTO = getErrorDTO(ConfigurationConstants.STATUS_BAD_REQUEST_MESSAGE_DEFAULT, description, code);
        logDebug(log, e);
        return new BadRequestException(errorDTO);
    }

    /**
     * This method is used to create a ConflictRequestException with the known errorCode and message.
     *
     * @param description Error Message Description.
     * @param code        Error Code.
     * @return ConflictRequestException with the given errorCode and description.
     */
    public static ConflictRequestException buildConflictRequestException(String description, String code,
                                                                         Log log, Throwable e) {

        ErrorDTO errorDTO = getErrorDTO(ConfigurationConstants.STATUS_BAD_REQUEST_MESSAGE_DEFAULT, description, code);
        logDebug(log, e);
        return new ConflictRequestException(errorDTO);
    }

    /**
     * This method is used to create a Forbidden Exception with the known errorCode and message.
     *
     * @param description Error Message Description.
     * @param code        Error Code.
     * @return ForbiddenException with the given errorCode and description.
     */
    public static ForbiddenException buildForbiddenException(String description, String code,
                                                             Log log, Throwable e) {

        ErrorDTO errorDTO = getErrorDTO(ConfigurationConstants.STATUS_BAD_REQUEST_MESSAGE_DEFAULT, description, code);
        logDebug(log, e);
        return new ForbiddenException(errorDTO);
    }

    /**
     * This method is used to create an InternalServerErrorException with the known errorCode.
     *
     * @param code Error Code.
     * @return a new InternalServerErrorException with default details.
     */
    public static InternalServerErrorException buildInternalServerErrorException(String code,
                                                                                 Log log, Throwable e) {

        ErrorDTO errorDTO = getErrorDTO(ConfigurationConstants.STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT,
                ConfigurationConstants.STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT, code);
        logError(log, e);
        return new InternalServerErrorException(errorDTO);
    }

    public static void logDebug(Log log, Throwable throwable) {

        if (log.isDebugEnabled()) {
            log.debug(ConfigurationConstants.STATUS_BAD_REQUEST_MESSAGE_DEFAULT, throwable);
        }
    }

    private static void logError(Log log, Throwable throwable) {

        log.error(throwable.getMessage(), throwable);
    }
}
