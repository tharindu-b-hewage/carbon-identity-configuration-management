package org.wso2.carbon.identity.configuration.mgt.endpoint;

import io.swagger.annotations.ApiParam;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantResponseDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.factories.SearchApiServiceFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/search")
@Consumes({"application/json"})
@Produces({"application/json"})
@io.swagger.annotations.Api(value = "/search", description = "the search API")
public class SearchApi {

    private final SearchApiService delegate = SearchApiServiceFactory.getSearchApi();

    @GET
    @Path("/{tenant-domain}/{resource-type}/{name}/{attribute}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Retrieve the tenant attribute.\n", notes = "This API is used to retrieve an attribute from the given tenant.\n", response = AttributeDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response searchTenantDomainResourceTypeNameAttributeGet(@ApiParam(value = "This represents the tenant domain name of the attribute to be retrieved.", required = true) @PathParam("tenant-domain") String tenantDomain,
                                                                   @ApiParam(value = "This represents the name of the attribute to be retrieved.", required = true) @PathParam("name") String name,
                                                                   @ApiParam(value = "This represents the type of the attribute to be retrieved and can either be the name or id.", required = true) @PathParam("resource-type") String resourceType,
                                                                   @ApiParam(value = "This represents an attribute key of the attribute to be retrieved.", required = true) @PathParam("attribute") String attribute,
                                                                   @ApiParam(value = "This represents the search query.") @Context SearchContext searchContext) {

        return delegate.searchTenantDomainResourceTypeNameAttributeGet(tenantDomain, name, resourceType, attribute, searchContext);
    }

    @GET
    @Path("/tenant")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Retrieve tenants based on search parameters.\n", notes = "This API is used to search for tenants with search filters.\n", response = TenantResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response searchTenantGet(@ApiParam(value = "This represents the search query.") @Context SearchContext searchContext) {

        return delegate.searchTenantGet(searchContext);
    }
}

