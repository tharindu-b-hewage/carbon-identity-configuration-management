package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;

import javax.ws.rs.core.Response;

public abstract class ResourceApiService {

    public abstract Response resourceGet(SearchContext searchContext);

    public abstract Response resourceNameDelete(String name);

    public abstract Response resourceNameGet(String name, SearchContext searchContext);

    public abstract Response resourceNamePatch(String name, ResourceDTO resource);

    public abstract Response resourceNamePost(String name, ResourceDTO resource);

    public abstract Response resourceNamePut(String name, ResourceDTO resource);
}

