package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceTypeApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeAddDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeDTO;

import java.net.URI;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BASE_PATH;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceTypeAddFromDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceTypeDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getSearchCondition;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleBadRequestResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleServerErrorResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleUnexpectedServerError;

public class ResourceTypeApiServiceImpl extends ResourceTypeApiService {

    private static final Log LOG = LogFactory.getLog(ResourceTypeApiServiceImpl.class);

    @Override
    public Response resourceTypePatch(ResourceTypeAddDTO resourceTypeAddDTO) {

        try {
            ResourceType resourceType = getConfigurationManager().updateResourceType(getResourceTypeAddFromDTO(resourceTypeAddDTO));
            return Response.created(new URI(BASE_PATH + "type")).entity(getResourceTypeDTO(resourceType)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceTypePost(ResourceTypeAddDTO resourceTypeAddDTO) {

        try {
            ResourceType resourceType = getConfigurationManager().addResourceType(getResourceTypeAddFromDTO(resourceTypeAddDTO));
            return Response.created(new URI(BASE_PATH + "type")).entity(getResourceTypeDTO(resourceType)).build(); // TODO: 12/9/18 Validate 200 OK created path in whole project
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceTypePut(ResourceTypeAddDTO resourceTypeAddDTO) {

        try {
            ResourceType resourceType = getConfigurationManager().replaceResourceType(getResourceTypeAddFromDTO(resourceTypeAddDTO));
            return Response.created(new URI(BASE_PATH + "type")).entity(getResourceTypeDTO(resourceType)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceTypeResourceTypeNameDelete(String resourceTypeName) {

        try {
            getConfigurationManager().deleteResourceType(resourceTypeName);
            return Response.ok().build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceTypeResourceTypeNameGet(String resourceTypeName, SearchContext searchContext) {

        try {
            ResourceType resourceType = getConfigurationManager().getResourceType(resourceTypeName, getSearchCondition(searchContext));
            return Response.ok().entity(getResourceTypeDTO(resourceType)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }
}
