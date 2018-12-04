package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.factories.ResourceApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceSearchResponseDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/resource")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/resource", description = "the resource API")
public class ResourceApi  {

   private final ResourceApiService delegate = ResourceApiServiceFactory.getResourceApi();

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve resources based on the optional search parmeters.\n", notes = "This API is used to retrieve resources for the tenant domain based on the optional search parameters.\n", response = ResourceSearchResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceGet(@ApiParam(value = "This represents the name of the resource to be retrieved.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents an optional search filer for resource names.") @QueryParam("resourceName")  List<String> resourceName,
    @ApiParam(value = "This represents an optional search filer for resource names.") @QueryParam("tenant")  List<String> tenant,
    @ApiParam(value = "This represents an optional search filer for resource names.") @QueryParam("type")  List<String> type)
    {
    return delegate.resourceGet(name,resourceName,tenant,type);
    }
    @DELETE
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Revoke the resource\n", notes = "This API is used to revoke a resource in the tenant domain given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceNameDelete(@ApiParam(value = "This represents the name of the resource to be retrieved.",required=true ) @PathParam("name")  String name)
    {
    return delegate.resourceNameDelete(name);
    }
    @GET
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve the resource based on optional search parameters.\n", notes = "This API is used to retrieve a resource based on optional search filters.\n", response = ResourceSearchResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceNameGet(@ApiParam(value = "This represents the name of the resource to be retrieved.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents an optional search filer for resource names.") @QueryParam("tenant")  List<String> tenant,
    @ApiParam(value = "This represents an optional search filer for resource names.") @QueryParam("type")  List<String> type)
    {
    return delegate.resourceNameGet(name,tenant,type);
    }
    @PATCH
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update existing resource\n", notes = "This API is used to update an existing resource given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceNamePatch(@ApiParam(value = "This represents the name of the resource to be added or updated.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the resource that needs to be updated." ,required=true ) ResourceDTO resource)
    {
    return delegate.resourceNamePatch(name,resource);
    }
    @POST
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create the resource\n", notes = "This API is used to store the resource given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceNamePost(@ApiParam(value = "This represents the name of the resource to be added.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the resource that needs to be added." ,required=true ) ResourceDTO resource)
    {
    return delegate.resourceNamePost(name,resource);
    }
    @PUT
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add or Replace the resource\n", notes = "This API is used to store or replace the resource given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceNamePut(@ApiParam(value = "This represents the name of the resource to be added or replaced.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the resource that need to be added or replaced." ,required=true ) ResourceDTO resource)
    {
    return delegate.resourceNamePut(name,resource);
    }
}

