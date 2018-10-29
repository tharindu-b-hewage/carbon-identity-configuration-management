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

package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ConfigurationApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_UNEXPECTED;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;

public class ConfigurationApiServiceImpl extends ConfigurationApiService {

    private static final Log LOG = LogFactory.getLog(ConfigurationApiServiceImpl.class);

    @Override
    public Response configurationDelete() {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationGet() {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationNameDelete(String name) {

        try {
            getConfigurationManager().deleteConfiguration(name);
            return Response.ok().build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response configurationNameGet(String name) {

        try {
            Configuration configuration = getConfigurationManager().getConfiguration(name);
            ConfigurationDTO configurationDTO = getConfigurationDTO(configuration);
            return Response.ok().entity(configurationDTO).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response configurationNamePatch(String name, TenantConfigurationsDTO configurationsties) {

        try {
            Configuration configuration = getConfigurationManager().getConfiguration(name);
            ConfigurationDTO configurationDTO = getConfigurationDTO(configuration);
            return Response.ok().entity(configurationDTO).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response configurationNamePost(String name, ConfigurationDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationNamePut(String name, ConfigurationDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationPatch(TenantConfigurationsDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationPost(TenantConfigurationsDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationPut(TenantConfigurationsDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    private Response handleBadRequestResponse(ConfigurationManagementClientException e) {

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

    private Response handleServerErrorResponse(ConfigurationManagementException e) {

        throw ConfigurationEndpointUtils.buildInternalServerErrorException(e.getErrorCode(), LOG, e);
    }

    private Response handleUnexpectedServerError(Throwable e) {

        throw ConfigurationEndpointUtils.buildInternalServerErrorException(ERROR_CODE_UNEXPECTED.getCode(), LOG, e);
    }

    private boolean isNotFoundError(ConfigurationManagementClientException e) {

        return ErrorMessages.ERROR_CODE_SELECT_CONFIGURATION_BY_ID.getCode().equals(e.getErrorCode());
    }

    private boolean isConflictError(ConfigurationManagementClientException e) {

        return ErrorMessages.ERROR_CODE_CONFIGURATION_ALREADY_EXIST.getCode().equals(e.getErrorCode());
    }

    private boolean isForbiddenError(ConfigurationManagementClientException e) {

        return ErrorMessages.ERROR_CODE_NO_USER_FOUND.getCode().equals(e.getErrorCode());
    }
}
