package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchBean;
import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.sql.SQLPrinterVisitor;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.AttributeValue;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeValueDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;

import java.net.URI;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BASE_PATH;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getAttributeValueDTO;
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
            Resource resource = getConfigurationManager().getResource();
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

        try {
            SearchCondition<SearchBean> condition = searchContext.getCondition(SearchBean.class);
            SQLPrinterVisitor<SearchBean> visitor = new SQLPrinterVisitor<>("MY_TABLE");
            condition.accept(visitor);
            String query = visitor.getQuery();
            AttributeValue attributeValue = getConfigurationManager().getAttributeValue(name, resourceType, attribute);
            AttributeValueDTO attributeValueDTO = getAttributeValueDTO(attributeValue);
            // TODO: 12/5/18 Response is wrong. GET can result in a ResourceSearchResponseElement
            return Response.ok().entity(attributeValueDTO).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
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
            getConfigurationManager().createAttribute(name, resourceType, new Attribute(name, attributeValue.getValue()));
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
            SearchCondition<SearchBean> condition = searchContext.getCondition(SearchBean.class);
            SQLPrinterVisitor<SearchBean> visitor = new SQLPrinterVisitor<>("MY_TABLE");
            condition.accept(visitor);
            String query = visitor.getQuery();
            Resource resource = getConfigurationManager().getResource(name, resourceType);
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
    public Response resourceResourceTypeNamePatch(String name, String resourceType, ResourceDTO resourceDTO) {

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
    public Response resourceResourceTypeNamePost(String name, String resourceType, ResourceDTO resourceDTO) {

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
    public Response resourceResourceTypeNamePut(String name, String resourceType, ResourceDTO resourceDTO) {

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
