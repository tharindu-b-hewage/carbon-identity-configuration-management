package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.AttributePathParameter;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceAddDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourcesDTO;

import java.net.URI;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BASE_PATH;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getAttributeDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getAttributeFromDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceAddFromDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourceDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourcesDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleBadRequestResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleServerErrorResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleUnexpectedServerError;

public class ResourceApiServiceImpl extends ResourceApiService {

    private static final Log LOG = LogFactory.getLog(ResourceApiServiceImpl.class);

    @Override
    public Response resourceGet() {

        try {
            Resources resources = getConfigurationManager().getResources();
            ResourcesDTO resourcesDTO = getResourcesDTO(resources);
            return Response.ok().entity(resourcesDTO).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeResourceTypeNameGet(String resourceTypeName) {

        try {
            Resources resources = getConfigurationManager().getResourcesByType(resourceTypeName);
            return Response.ok().entity(getResourcesDTO(resources)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypePatch(String resourceType, ResourceAddDTO resourceAddDTO) {

        try {
            Resource resource = getConfigurationManager().updateResource(resourceType, getResourceAddFromDTO(resourceAddDTO));
            return Response.created(new URI(BASE_PATH + resource.getResourceName())).entity(getResourceDTO(resource)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypePost(String resourceType, ResourceAddDTO resourceAddDTO) {

        try {
            Resource resource = getConfigurationManager().addResource(resourceType, getResourceAddFromDTO(resourceAddDTO));
            return Response.created(new URI(BASE_PATH + resource.getResourceName())).entity(getResourceDTO(resource)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypePut(String resourceType, ResourceAddDTO resourceAddDTO) {

        try {
            Resource resource = getConfigurationManager().replaceResource(resourceType, getResourceAddFromDTO(resourceAddDTO));
            return Response.created(new URI(BASE_PATH + resource.getResourceName())).entity(getResourceDTO(resource)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeResourceNameAttributeKeyDelete(String resourceName, String resourceType, String attributeKey) {

        try {
            getConfigurationManager().deleteAttribute(resourceType, resourceName, attributeKey);
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
    public Response resourceResourceTypeResourceNameAttributeKeyGet(String resourceName, String resourceType,
                                                                    String attributeKey) {

        try {
            Attribute attribute = getConfigurationManager().getAttribute(resourceType, resourceName, attributeKey);
            return Response.ok().entity(getAttributeDTO(attribute)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeResourceNameDelete(String resourceName, String resourceType) {

        try {
            getConfigurationManager().deleteResource(resourceType, resourceName);
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
    public Response resourceResourceTypeResourceNameGet(String resourceName, String resourceType) {

        try {
            Resource resource = getConfigurationManager().getResource(resourceType, resourceName);
            return Response.ok().entity(getResourceDTO(resource)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeResourceNamePatch(String resourceName, String resourceType, AttributeDTO attributeDTO) {

        try {
            Attribute attribute = getConfigurationManager().updateAttribute(resourceType, resourceName, getAttributeFromDTO(attributeDTO));
            return Response.created(new URI(BASE_PATH + resourceName)).entity(getAttributeDTO(attribute)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeResourceNamePost(String resourceName, String resourceType, AttributeDTO attributeDTO) {

        try {
            Attribute attribute = getConfigurationManager().addAttribute(resourceType, resourceName, getAttributeFromDTO(attributeDTO));
            return Response.created(new URI(BASE_PATH + resourceName)).entity(getAttributeDTO(attribute)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }

    @Override
    public Response resourceResourceTypeResourceNamePut(String resourceName, String resourceType, AttributeDTO attributeDTO) {

        try {
            Attribute attribute = getConfigurationManager().replaceAttribute(
                    resourceType, resourceName, getAttributeFromDTO(attributeDTO));
            return Response.created(new URI(BASE_PATH + resourceName)).entity(getAttributeDTO(attribute)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }
}
