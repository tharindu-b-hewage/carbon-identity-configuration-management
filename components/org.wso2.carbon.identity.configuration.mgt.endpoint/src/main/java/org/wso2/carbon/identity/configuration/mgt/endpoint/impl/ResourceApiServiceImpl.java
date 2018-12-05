package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchBean;
import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.sql.SQLPrinterVisitor;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceFile;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils;
import org.wso2.carbon.identity.configuration.mgt.core.util.model.ConfigurationSearchFilter;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BASE_PATH;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_UNEXPECTED;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.HTTP_SEARCH_FILTER_ATTRIBUTE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.HTTP_SEARCH_FILTER_FILE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.HTTP_SEARCH_FILTER_TENANT;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.HTTP_SEARCH_FILTER_TYPE;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceFromDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceDTO;

public class ResourceApiServiceImpl extends ResourceApiService {

    private static final Log LOG = LogFactory.getLog(ResourceApiServiceImpl.class);

    @Override
    public Response resourceGet(SearchContext searchContext) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response resourceNameDelete(String name) {

        try {
            getConfigurationManager().deleteResource(name);
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
    public Response resourceNameGet(String name, SearchContext searchContext) {

        try {
            SearchCondition<SearchBean> condition = searchContext.getCondition(SearchBean.class);
            SQLPrinterVisitor<SearchBean> visitor = new SQLPrinterVisitor<>("MY_TABLE");
            condition.accept(visitor);
            String query = visitor.getQuery();
            Resource resource = getConfigurationManager().getResource(name, null);
            ResourceDTO resourceDTO = getResourceDTO(resource);
            return Response.ok().entity(resourceDTO).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response resourceNamePatch(String name, ResourceDTO resourceDTO) {

        try {
            getConfigurationManager().updateResource(name, getResourceFromDTO(resourceDTO));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response resourceNamePost(String name, ResourceDTO resourceDTO) {

        try {
            getConfigurationManager().addResource(name, getResourceFromDTO(resourceDTO));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
    }

    @Override
    public Response resourceNamePut(String name, ResourceDTO resourceDTO) {

        try {
            getConfigurationManager().replaceResource(name, getResourceFromDTO(resourceDTO));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable);
        }
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
