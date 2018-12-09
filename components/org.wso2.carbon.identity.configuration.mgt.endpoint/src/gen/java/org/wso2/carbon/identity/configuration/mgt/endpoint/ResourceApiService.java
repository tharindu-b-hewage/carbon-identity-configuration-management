package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceAddDTO;

import javax.ws.rs.core.Response;

public abstract class ResourceApiService {

    public abstract Response resourceGet(SearchContext searchContext);

    public abstract Response resourceResourceTypePatch(String resourceType, ResourceAddDTO resource);

    public abstract Response resourceResourceTypePost(String resourceType, ResourceAddDTO resource);

    public abstract Response resourceResourceTypePut(String resourceType, ResourceAddDTO resource);

    public abstract Response resourceResourceTypeResourceNameAttributeKeyDelete(String resourceName, String resourceType, String attributeKey);

    public abstract Response resourceResourceTypeResourceNameAttributeKeyGet(String resourceName, String resourceType, String attributeKey, SearchContext searchContext);

    public abstract Response resourceResourceTypeResourceNameDelete(String resourceName, String resourceType);

    public abstract Response resourceResourceTypeResourceNameGet(String resourceName, String resourceType, SearchContext searchContext);

    public abstract Response resourceResourceTypeResourceNamePatch(String resourceName, String resourceType, AttributeDTO attribute);

    public abstract Response resourceResourceTypeResourceNamePost(String resourceName, String resourceType, AttributeDTO attribute);

    public abstract Response resourceResourceTypeResourceNamePut(String resourceName, String resourceType, AttributeDTO attribute);

    public abstract Response resourceResourceTypeResourceTypeNameGet(String resourceTypeName, SearchContext searchContext);
}

