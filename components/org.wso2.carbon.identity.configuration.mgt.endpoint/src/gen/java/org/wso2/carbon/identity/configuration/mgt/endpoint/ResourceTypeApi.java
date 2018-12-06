package org.wso2.carbon.identity.configuration.mgt.endpoint;

import io.swagger.annotations.ApiParam;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeCreateDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.factories.ResourceTypeApiServiceFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/resource-type")
@Consumes({"application/json"})
@Produces({"application/json"})
@io.swagger.annotations.Api(value = "/resource-type", description = "the resource-type API")
public class ResourceTypeApi {

    private final ResourceTypeApiService delegate = ResourceTypeApiServiceFactory.getResourceTypeApi();

    @DELETE

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Revoke resource type.\n", notes = "This API is used to delete an existing resource type.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Ok"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response resourceTypeDelete(@ApiParam(value = "This represents the resource type to be retrieved.") @QueryParam("name") String name,
                                       @ApiParam(value = "This represents the resource type to be retrieved.") @QueryParam("id") String id) {

        return delegate.resourceTypeDelete(name, id);
    }

    @GET

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Get the resource type.\n", notes = "This API is used to get an existing resource type.\n", response = ResourceTypeDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Ok"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response resourceTypeGet(@ApiParam(value = "This represents the resource type to be retrieved.") @QueryParam("name") String name,
                                    @ApiParam(value = "This represents the resource type to be retrieved.") @QueryParam("id") String id,
                                    @ApiParam(value = "This represents the search query.") @Context SearchContext searchContext) {

        return delegate.resourceTypeGet(name, id, searchContext);
    }

    @PATCH

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Update existing resource type.\n", notes = "This API is used to update an existing resource type.\n", response = ResourceTypeDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response resourceTypePatch(@ApiParam(value = "This represents the resource type to update the existing.", required = true) ResourceTypeCreateDTO type) {

        return delegate.resourceTypePatch(type);
    }

    @POST

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Create the resource type.\n", notes = "This API is used to create a new resource type.\n", response = ResourceTypeDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response resourceTypePost(@ApiParam(value = "This represents the resource type to be added.", required = true) ResourceTypeCreateDTO type) {

        return delegate.resourceTypePost(type);
    }

    @PUT

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Create or replace the resource type.\n", notes = "This API is used to create or replace a new resource type.\n", response = ResourceTypeDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response resourceTypePut(@ApiParam(value = "This represents the resource type to be added.", required = true) ResourceTypeCreateDTO type) {

        return delegate.resourceTypePut(type);
    }
}

