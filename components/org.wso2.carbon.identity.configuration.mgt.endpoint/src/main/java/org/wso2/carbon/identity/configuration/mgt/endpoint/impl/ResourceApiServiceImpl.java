package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.AttributePathParameter;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeValueDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceAddDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;

import java.net.URI;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BASE_PATH;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceFromDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleBadRequestResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleServerErrorResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleUnexpectedServerError;

public class ResourceApiServiceImpl extends ResourceApiService {

    private static final Log LOG = LogFactory.getLog(ResourceApiServiceImpl.class);

    @Override
    public Response resourceGet(SearchContext searchContext) {

        try {
            Resource resource = getConfigurationManager().getResource(searchContext);
            ResourceDTO resourceDTO = getResourceDTO(resource);
            // TODO: 12/5/18 Response is wrong. GET can result in a ResourceSearchResponseElement
            return Response.ok().entity(resourceDTO).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeNameAttributeDelete(String name, String resourceType, String attribute) {

        try {
            getConfigurationManager().deleteAttribute(name, resourceType, attribute);
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
    public Response resourceResourceTypeNameAttributeGet(String name, String resourceType, String attribute, SearchContext searchContext) {

        try{
            AttributePathParameter attributePathParameter = new AttributePathParameter();
            attributePathParameter.setAttributeKey(attribute);
            attributePathParameter.setResourceName(name);
            attributePathParameter.setResourceType(resourceType);

            getConfigurationManager().getAttributeValue(attributePathParameter, searchContext);
        } catch (ConfigurationManagementException e) {
            // Do something
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response resourceResourceTypeNameAttributePatch(String name, String resourceType, String attribute, AttributeValueDTO attributeValue) {

        try {
            getConfigurationManager().updateAttribute(name, resourceType, new Attribute(name, attributeValue.getValue()));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeNameAttributePost(String name, String resourceType, String attribute, AttributeValueDTO attributeValue) {

        try {
            getConfigurationManager().addAttribute(name, resourceType, new Attribute(name, attributeValue.getValue()));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeNameAttributePut(String name, String resourceType, String attribute, AttributeValueDTO attributeValue) {

        try {
            getConfigurationManager().replaceAttribute(name, resourceType, new Attribute(name, attributeValue.getValue()));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeNameDelete(String name, String resourceType) {

        try {
            getConfigurationManager().deleteResource(name, resourceType);
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
    public Response resourceResourceTypeNameGet(String name, String resourceType, SearchContext searchContext) {
        try {
            getConfigurationManager().getResource(name, resourceType, searchContext);
        } catch (ConfigurationManagementException e) {

        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response resourceResourceTypeNamePatch(String name, String resourceType, ResourceAddDTO resource) {

        try {
            getConfigurationManager().updateResource(name, resourceType, getResourceFromDTO(resourceDTO));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeNamePost(String name, String resourceType, ResourceAddDTO resource) {

        try {
            getConfigurationManager().addResource(name, resourceType, getResourceFromDTO(resourceDTO));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeNamePut(String name, String resourceType, ResourceAddDTO resource) {

        try {
            getConfigurationManager().replaceResource(name, resourceType, getResourceFromDTO(resourceDTO));
            return Response.created(new URI(BASE_PATH + name)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }
}
