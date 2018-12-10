package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.endpoint.SearchApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getResourcesDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getSearchCondition;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleBadRequestResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleServerErrorResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleUnexpectedServerError;
public class SearchApiServiceImpl extends SearchApiService {

    private static final Log LOG = LogFactory.getLog(ResourceTypeApiServiceImpl.class);

    @Override
    public Response searchGet(SearchContext searchContext) {

        try {
            Resources resources = ConfigurationEndpointUtils.getConfigurationManager().getTenantResources(getSearchCondition(searchContext));
            return Response.ok().entity(getResourcesDTO(resources)).build();
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }
}
