package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeValueDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;

import javax.ws.rs.core.Response;

public abstract class ResourceApiService {

    public abstract Response resourceGet(SearchContext searchContext);

    public abstract Response resourceResourceTypeNameAttributeDelete(String name, String resourceType, String attribute);

    public abstract Response resourceResourceTypeNameAttributeGet(String name, String resourceType, String attribute, SearchContext searchContext);

    public abstract Response resourceResourceTypeNameAttributePatch(String name, String resourceType, String attribute, AttributeValueDTO resource);

    public abstract Response resourceResourceTypeNameAttributePost(String name, String resourceType, String attribute, AttributeValueDTO resource);

    public abstract Response resourceResourceTypeNameAttributePut(String name, String resourceType, String attribute, AttributeValueDTO resource);

    public abstract Response resourceResourceTypeNameDelete(String name, String resourceType);

    public abstract Response resourceResourceTypeNameGet(String name, String resourceType, SearchContext searchContext);

    public abstract Response resourceResourceTypeNamePatch(String name, String resourceType, ResourceDTO resource);

    public abstract Response resourceResourceTypeNamePost(String name, String resourceType, ResourceDTO resource);

    public abstract Response resourceResourceTypeNamePut(String name, String resourceType, ResourceDTO resource);
}

