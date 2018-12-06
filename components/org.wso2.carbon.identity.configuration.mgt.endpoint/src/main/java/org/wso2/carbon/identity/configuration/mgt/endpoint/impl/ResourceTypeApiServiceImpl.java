package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceTypeApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeCreateDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeDTO;

import java.net.URI;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BASE_PATH;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceTypeCreateFromDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceTypeDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleBadRequestResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleServerErrorResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleUnexpectedServerError;

public class ResourceTypeApiServiceImpl extends ResourceTypeApiService {

    private static final Log LOG = LogFactory.getLog(ResourceTypeApiServiceImpl.class);

    @Override
    public Response resourceTypeDelete(java.lang.String name, java.lang.String id) {

        try {
            getConfigurationManager().deleteResourceType(name, id);
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
    public Response resourceTypeGet(java.lang.String name, java.lang.String id, SearchContext searchContext) {

        try {
            ResourceType resourceType = getConfigurationManager().getResourceType(name, id);
            ResourceTypeDTO resourceTypeDTO = getResourceTypeDTO(resourceType);
            return Response.ok().entity(resourceTypeDTO).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceTypePatch(ResourceTypeCreateDTO resourceTypeCreate) {

        try {
            getConfigurationManager().updateResourceType(getResourceTypeCreateFromDTO(resourceTypeCreate));
            return Response.created(new URI(BASE_PATH + "type")).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceTypePost(ResourceTypeCreateDTO resourceTypeCreate) {

        try {
            getConfigurationManager().addResourceType(getResourceTypeCreateFromDTO(resourceTypeCreate));
            return Response.created(new URI(BASE_PATH + "type")).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceTypePut(ResourceTypeCreateDTO resourceTypeCreate) {

        try {
            getConfigurationManager().replaceResourceType(getResourceTypeCreateFromDTO(resourceTypeCreate));
            return Response.created(new URI(BASE_PATH + "type")).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }
}
