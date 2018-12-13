package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.SearchParseException;
import org.apache.cxf.jaxrs.ext.search.visitor.PropertyValidationException;
import org.apache.olingo.odata2.api.uri.expression.ExpressionParserException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resources;
import org.wso2.carbon.identity.configuration.mgt.endpoint.SearchApiService;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.buildFlaggedSQLFromSearchExpression;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleBadRequestResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleSearchQueryParseError;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleServerErrorResponse;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.handleUnexpectedServerError;

public class SearchApiServiceImpl extends SearchApiService {

    private static final Log LOG = LogFactory.getLog(SearchApiServiceImpl.class);

    @Override
    public Response searchGet(SearchContext searchContext) {

        try {
            String flaggedSQLFromSearchExpression = buildFlaggedSQLFromSearchExpression(searchContext);
            Resources resources = getConfigurationManager().getTenantResources(
                    flaggedSQLFromSearchExpression
            );
            return Response.ok().entity(resources).build();
        } catch (SearchParseException e) {
            return handleSearchQueryParseError(e, LOG);
        } catch (PropertyValidationException e) {
            return handleSearchQueryParseError(e, LOG);
        } catch (ConfigurationManagementClientException e) {
            return handleBadRequestResponse(e, LOG);
        } catch (ConfigurationManagementException e) {
            return handleServerErrorResponse(e, LOG);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, LOG);
        }
    }
}
