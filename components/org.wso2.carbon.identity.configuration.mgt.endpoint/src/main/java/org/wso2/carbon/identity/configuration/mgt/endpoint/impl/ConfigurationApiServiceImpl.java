package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;
import org.wso2.carbon.identity.configuration.mgt.core.model.ConfigurationChangeResponse;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ConfigurationApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils;

import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BASE_PATH;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_UNEXPECTED;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationChangeResponseDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationFromDTO;
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
    public Response configurationNameGet(String name, List<String> tenant) {

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
    public Response configurationNamePatch(String name, ConfigurationDTO configuration) {

        try {
            ConfigurationChangeResponse configurationChangeResponse = getConfigurationManager()
                    .updateConfiguration(name, getConfigurationFromDTO(configuration));
            return Response.created(new URI(BASE_PATH + name))
                    .entity(getConfigurationChangeResponseDTO(configurationChangeResponse)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response configurationNamePost(String name, ConfigurationDTO configuration) {

        try {
            ConfigurationChangeResponse configurationChangeResponse = getConfigurationManager()
                    .addConfiguration(name, getConfigurationFromDTO(configuration));
            return Response.created(new URI(BASE_PATH + name))
                    .entity(getConfigurationChangeResponseDTO(configurationChangeResponse)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response configurationNamePut(String name, ConfigurationDTO configuration) {

        try {
            ConfigurationChangeResponse configurationChangeResponse = getConfigurationManager()
                    .replaceConfiguration(name, getConfigurationFromDTO(configuration));
            return Response.created(new URI(BASE_PATH + name))
                    .entity(getConfigurationChangeResponseDTO(configurationChangeResponse)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
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

        return ConfigurationConstants.ErrorMessages.ERROR_CODE_SELECT_CONFIGURATION_BY_ID.getCode().equals(e.getErrorCode());
    }

    private boolean isConflictError(ConfigurationManagementClientException e) {

        return ConfigurationConstants.ErrorMessages.ERROR_CODE_CONFIGURATION_ALREADY_EXIST.getCode().equals(e.getErrorCode());
    }

    private boolean isForbiddenError(ConfigurationManagementClientException e) {

        return ConfigurationConstants.ErrorMessages.ERROR_CODE_NO_USER_FOUND.getCode().equals(e.getErrorCode());
    }
}
