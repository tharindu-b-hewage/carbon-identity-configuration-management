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
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.AttributeValue;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceFile;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeCreate;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeValueDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceFileDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeCreateDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.BadRequestException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.ConflictRequestException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.ForbiddenException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.InternalServerErrorException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_UNEXPECTED;

//import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationChangeResponseDTO;
//import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;

/**
 * Utility functions required for configuration endpoint
 */
public class ConfigurationEndpointUtils {

    public static ConfigurationManager getConfigurationManager() {

        return (ConfigurationManager) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(ConfigurationManager.class, null);
    }

    public static ResourceDTO getResourceDTO(Resource resource) {

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResourceName(resource.getResourceName());
        resourceDTO.setAttributes(
                resource.getAttribute().stream().map(
                        ConfigurationEndpointUtils::getAttributeDTO
                ).collect(Collectors.toList())
        );
        resourceDTO.setFile(getResourceFileDTO(resource.getFile()));
        resourceDTO.setResourceType(resource.getResourceType());
        return resourceDTO;
    }

    public static ResourceTypeDTO getResourceTypeDTO(ResourceType resourceType) {

        ResourceTypeDTO resourceTypeDTO = new ResourceTypeDTO();
        resourceTypeDTO.setName(resourceType.getName());
        resourceTypeDTO.setId(resourceType.getId());
        resourceTypeDTO.setDescription(resourceType.getDescription());
        return resourceTypeDTO;
    }

    public static ResourceFileDTO getResourceFileDTO(ResourceFile resourceFile) {

        ResourceFileDTO resourceFileDTO = new ResourceFileDTO();
        resourceFileDTO.setValue(resourceFile.getValue());
        return resourceFileDTO;
    }

    public static AttributeValueDTO getAttributeValueDTO(AttributeValue attributeValue) {

        AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
        attributeValueDTO.setValue(attributeValue.getValue());
        return attributeValueDTO;
    }

    public static Resource getResourceFromDTO(ResourceDTO resourceDTO) {

        Resource resource = new Resource(
                resourceDTO.getResourceName(), resourceDTO.getResourceType()
        );
        resource.setFile(getResourceFileFromDTO(resourceDTO.getFile()));
        resource.setAttribute(
                resourceDTO.getAttributes().stream().map(
                        ConfigurationEndpointUtils::getAttributeFromDTO
                ).collect(Collectors.toList())
        );
        return resource;
    }

    public static ResourceFile getResourceFileFromDTO(ResourceFileDTO resourceFileDTO) {

        ResourceFile resourceFile = new ResourceFile();
        resourceFile.setValue(resourceFileDTO.getValue());
        return resourceFile;
    }

    public static ResourceType getResourceTypeFromDTO(ResourceTypeDTO resourceTypeDTO) {

        ResourceType resourceType = new ResourceType(resourceTypeDTO.getName());
        resourceType.setId(resourceTypeDTO.getId());
        resourceType.setDescription(resourceTypeDTO.getDescription());
        return resourceType;
    }

    public static ResourceTypeCreate getResourceTypeCreateFromDTO(ResourceTypeCreateDTO resourceTypeCreateDTO) {

        ResourceTypeCreate resourceTypeCreate = new ResourceTypeCreate();
        resourceTypeCreate.setName(resourceTypeCreateDTO.getName());
        resourceTypeCreate.setDescription(resourceTypeCreateDTO.getDescription());
        return resourceTypeCreate;
    }

    private static List<Attribute> getAttributesFromDTO(List<AttributeDTO> attributeDTOS) {

        return attributeDTOS
                .stream()
                .map(ConfigurationEndpointUtils::getAttributeFromDTO)
                .collect(Collectors.toList());
    }

    private static Attribute getAttributeFromDTO(AttributeDTO attributeDTO) {

        return new Attribute(attributeDTO.getKey(), attributeDTO.getValue());
    }

    private static AttributeDTO getAttributeDTO(Attribute attribute) {

        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setKey(attribute.getKey());
        attributeDTO.setValue(attribute.getValue());
        return attributeDTO;
    }

    private static ErrorDTO getErrorDTO(java.lang.String message, java.lang.String description, java.lang.String code) {

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(code);
        errorDTO.setMessage(message);
        errorDTO.setDescription(description);
        return errorDTO;
    }

    public static Response handleBadRequestResponse(ConfigurationManagementClientException e, Log LOG) {

        if (isNotFoundError(e)) {
            throw ConfigurationEndpointUtils.buildNotFoundRequestException(e.getMessage(), e.getErrorCode(), LOG, e);
        }

        if (isConflictError(e)) {
            throw ConfigurationEndpointUtils.buildConflictRequestException(e.getMessage(), e.getErrorCode(), LOG, e);
        }

        if (isForbiddenError(e)) {
            throw ConfigurationEndpointUtils.buildForbiddenException(e.getMessage(), e.getErrorCode(), LOG, e);
        }
        throw ConfigurationEndpointUtils.buildBadRequestException(e.getMessage(), e.getErrorCode(), LOG, e);
    }

    public static Response handleServerErrorResponse(ConfigurationManagementException e, Log LOG) {

        throw ConfigurationEndpointUtils.buildInternalServerErrorException(e.getErrorCode(), LOG, e);
    }

    public static Response handleUnexpectedServerError(Throwable e, Log LOG) {

        throw ConfigurationEndpointUtils.buildInternalServerErrorException(ERROR_CODE_UNEXPECTED.getCode(), LOG, e);
    }

    private static boolean isNotFoundError(ConfigurationManagementClientException e) {

        return ConfigurationConstants.ErrorMessages.ERROR_CODE_SELECT_CONFIGURATION_BY_ID.getCode().equals(e.getErrorCode());
    }

    private static boolean isConflictError(ConfigurationManagementClientException e) {

        return ConfigurationConstants.ErrorMessages.ERROR_CODE_CONFIGURATION_ALREADY_EXIST.getCode().equals(e.getErrorCode());
    }

    private static boolean isForbiddenError(ConfigurationManagementClientException e) {

        return ConfigurationConstants.ErrorMessages.ERROR_CODE_NO_USER_FOUND.getCode().equals(e.getErrorCode());
    }

    public static NotFoundException buildNotFoundRequestException(java.lang.String description, java.lang.String code,
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
    public static BadRequestException buildBadRequestException(java.lang.String description, java.lang.String code,
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
    public static ConflictRequestException buildConflictRequestException(java.lang.String description, java.lang.String code,
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
    public static ForbiddenException buildForbiddenException(java.lang.String description, java.lang.String code,
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
    public static InternalServerErrorException buildInternalServerErrorException(java.lang.String code,
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
