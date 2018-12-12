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
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceFile;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.core.model.search.SearchCondition;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceAddDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceFileDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeAddDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourcesDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.BadRequestException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.ConflictRequestException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.ForbiddenException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.InternalServerErrorException;
import org.wso2.carbon.identity.configuration.mgt.endpoint.exception.NotFoundException;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_ALREADY_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_DOES_NOT_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_UNEXPECTED;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ODATA2_API_URI_EXPRESSION_PARSER_ERROR;

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
        resourceDTO.setResourceType(resource.getResourceType());
        resourceDTO.setLastModified(resource.getLastModified());
        resourceDTO.setTenantDomain(resource.getTenantDomain());
        resourceDTO.setResourceId(resource.getResourceId());
        resourceDTO.setTenantDomain(resource.getTenantDomain());
        resourceDTO.setAttributes(
                resource.getAttribute() != null ?
                        resource.getAttribute()
                                .stream()
                                .map(ConfigurationEndpointUtils::getAttributeDTO)
                                .collect(Collectors.toList())
                        : new ArrayList<>(0)
        );
        resourceDTO.setFiles(resource.getFile() != null ?
                resource.getFile()
                        .stream()
                        .map(ConfigurationEndpointUtils::getResourceFileDTO)
                        .collect(Collectors.toList())
                : new ArrayList<>(0)
        );
        return resourceDTO;
    }

    public static ResourcesDTO getResourcesDTO(Resources resources) {

        ResourcesDTO resourcesDTO = new ResourcesDTO();
        resourcesDTO.setResources(resources.getResources()
                .stream()
                .map(ConfigurationEndpointUtils::getResourceDTO)
                .collect(Collectors.toList())
        );
        return resourcesDTO;
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
        resourceFileDTO.setPath(resourceFile.getValue());
        return resourceFileDTO;
    }

    public static ResourceAdd getResourceAddFromDTO(ResourceAddDTO resourceAddDTO) {

        ResourceAdd resourceAdd = new ResourceAdd();
        resourceAdd.setName(resourceAddDTO.getName());
        resourceAdd.setAttributes(resourceAddDTO.getAttributes()
                .stream()
                .map(ConfigurationEndpointUtils::getAttributeFromDTO)
                .collect(Collectors.toList()));
        return resourceAdd;
    }

    public static ResourceFile getResourceFileFromDTO(ResourceFileDTO resourceFileDTO) {

        ResourceFile resourceFile = new ResourceFile();
        resourceFile.setValue(resourceFileDTO.getPath());
        return resourceFile;
    }

    public static ResourceTypeAdd getResourceTypeAddFromDTO(ResourceTypeAddDTO resourceTypeAddDTO) {

        ResourceTypeAdd resourceTypeAdd = new ResourceTypeAdd();
        resourceTypeAdd.setName(resourceTypeAddDTO.getName());
        resourceTypeAdd.setDescription(resourceTypeAddDTO.getDescription());
        return resourceTypeAdd;
    }

    public static Attribute getAttributeFromDTO(AttributeDTO attributeDTO) {

        return new Attribute(attributeDTO.getKey(), attributeDTO.getValue());
    }

    public static AttributeDTO getAttributeDTO(Attribute attribute) {

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

        return ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS.getCode().equals(e.getErrorCode()) ||
                ERROR_CODE_RESOURCE_DOES_NOT_EXISTS.getCode().equals(e.getErrorCode());
    }

    private static boolean isConflictError(ConfigurationManagementClientException e) {

        return ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS.getCode().equals(e.getErrorCode()) ||
                ERROR_CODE_RESOURCE_ALREADY_EXISTS.getCode().equals(e.getErrorCode());
    }

    private static boolean isForbiddenError(ConfigurationManagementClientException e) {

        return ConfigurationConstants.ErrorMessages.ERROR_CODE_NO_USER_FOUND.getCode().equals(e.getErrorCode());
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

        String description = generateInternalServerErrorDescription(e.getMessage());
        ErrorDTO errorDTO = getErrorDTO(
                ConfigurationConstants.STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT,
                description != null ? description : ConfigurationConstants.STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT,
                code
        );
        logError(log, e);
        return new InternalServerErrorException(errorDTO);
    }

    /*
    This method will generate use-case specific description messages for the error message.
     */
    private static String generateInternalServerErrorDescription(String errorMessage) {

        // Handle errors while parsing Odata expressions with apache cxf support for jax-rs search.
        if (errorMessage.equals(ODATA2_API_URI_EXPRESSION_PARSER_ERROR)) {
            return ConfigurationConstants.STATUS_ODATA_EXPRESSION_PARSER_ERROR_MESSAGE;
        }
        return null;
    }

    public static SearchCondition getSearchCondition(SearchContext searchContext) {
        // TODO: 12/10/18 Implementation
        return new SearchCondition(null);
    }

    private static void logDebug(Log log, Throwable throwable) {

        if (log.isDebugEnabled()) {
            log.debug(ConfigurationConstants.STATUS_BAD_REQUEST_MESSAGE_DEFAULT, throwable);
        }
    }

    private static void logError(Log log, Throwable throwable) {

        log.error(throwable.getMessage(), throwable);
    }
}
