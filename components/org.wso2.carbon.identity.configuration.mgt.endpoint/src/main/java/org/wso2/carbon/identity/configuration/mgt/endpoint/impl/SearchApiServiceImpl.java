package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.TenantAttributePathParameter;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.configuration.mgt.endpoint.SearchApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils;

import javax.ws.rs.core.Response;

public class SearchApiServiceImpl extends SearchApiService {

    @Override
    public Response searchTenantDomainResourceTypeNameAttributeGet(String tenantDomain, String name, String resourceType, String attribute, SearchContext searchContext) {

        try {
            TenantAttributePathParameter tenantAttributePathParameter = new TenantAttributePathParameter();
            tenantAttributePathParameter.setAttributeKey(attribute);
            tenantAttributePathParameter.setResourceName(name);
            tenantAttributePathParameter.setResourceType(resourceType);
            tenantAttributePathParameter.setResourceType(tenantDomain);

            ConfigurationEndpointUtils.getConfigurationManager().getTenantAttribute(tenantAttributePathParameter, searchContext);
        } catch (ConfigurationManagementException e) {

        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response searchTenantGet(SearchContext searchContext) {
        try {
            ConfigurationEndpointUtils.getConfigurationManager().getTenants(searchContext);
        } catch (ConfigurationManagementException e) {

        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
