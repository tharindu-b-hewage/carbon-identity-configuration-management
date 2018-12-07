package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import javax.ws.rs.core.Response;

public abstract class SearchApiService {

    public abstract Response searchTenantDomainResourceTypeNameAttributeGet(String tenantDomain, String name, String resourceType, String attribute, SearchContext searchContext);

    public abstract Response searchTenantGet(SearchContext searchContext);
}

