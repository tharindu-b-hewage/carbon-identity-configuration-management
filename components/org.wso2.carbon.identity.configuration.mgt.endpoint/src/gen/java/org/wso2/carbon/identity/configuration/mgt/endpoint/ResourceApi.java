package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.factories.ResourceApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceSearchResponseDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeValueDTO;
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

    public Response resourceGet()
    {
    return delegate.resourceGet();
    }
    @DELETE
    @Path("/{resource-type}/{name}/{attribute}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Revoke the attribute\n", notes = "This API is used to revoke a attribute in the tenant domain given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNameAttributeDelete(@ApiParam(value = "This represents the name of the attribute to be retrieved.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the attribute to be deleted and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents an attribute key of the attribute to be deleted.",required=true ) @PathParam("attribute")  String attribute)
    {
    return delegate.resourceResourceTypeNameAttributeDelete(name,resourceType,attribute);
    }
    @GET
    @Path("/{resource-type}/{name}/{attribute}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve the attribute based on optional search parameters.\n", notes = "This API is used to retrieve a attribute based on optional search filters.\n", response = AttributeDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNameAttributeGet(@ApiParam(value = "This represents the name of the attribute to be retrieved.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the attribute to be retrieved and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents an attribute key of the attribute to be retrieved.",required=true ) @PathParam("attribute")  String attribute)
    {
    return delegate.resourceResourceTypeNameAttributeGet(name,resourceType,attribute);
    }
    @PATCH
    @Path("/{resource-type}/{name}/{attribute}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update existing attribute\n", notes = "This API is used to update an existing attribute given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNameAttributePatch(@ApiParam(value = "This represents the name of the attribute to be added or updated.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the attribute to be added or updated and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents an attribute key of the attribute to be added or updated.",required=true ) @PathParam("attribute")  String attribute,
    @ApiParam(value = "This represents the corresponding attribute value that needs to be added or updated." ,required=true ) AttributeValueDTO attributeValue)
    {
    return delegate.resourceResourceTypeNameAttributePatch(name,resourceType,attribute,attributeValue);
    }
    @POST
    @Path("/{resource-type}/{name}/{attribute}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create the attribute\n", notes = "This API is used to store the attribute given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNameAttributePost(@ApiParam(value = "This represents the name of the attribute to be added.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the attribute to be added and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents an attribute key of the attribute to be added.",required=true ) @PathParam("attribute")  String attribute,
    @ApiParam(value = "This represents the corresponding attribute value that needs to be added." ,required=true ) AttributeValueDTO attributeValue)
    {
    return delegate.resourceResourceTypeNameAttributePost(name,resourceType,attribute,attributeValue);
    }
    @PUT
    @Path("/{resource-type}/{name}/{attribute}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add or Replace the attribute\n", notes = "This API is used to store or replace the attribute given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNameAttributePut(@ApiParam(value = "This represents the name of the attribute to be added or replaced.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the attribute to be added or replaced and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents an attribute key of the attribute to be added or replaced.",required=true ) @PathParam("attribute")  String attribute,
    @ApiParam(value = "This represents the corresponding attribute value that needs to be added or replaced." ,required=true ) AttributeValueDTO attributeValue)
    {
    return delegate.resourceResourceTypeNameAttributePut(name,resourceType,attribute,attributeValue);
    }
    @DELETE
    @Path("/{resource-type}/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Revoke the resource\n", notes = "This API is used to revoke a resource in the tenant domain given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNameDelete(@ApiParam(value = "This represents the name of the resource to be retrieved.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the resource to be added and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType)
    {
    return delegate.resourceResourceTypeNameDelete(name,resourceType);
    }
    @GET
    @Path("/{resource-type}/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve the resource based on optional search parameters.\n", notes = "This API is used to retrieve a resource based on optional search filters.\n", response = ResourceDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNameGet(@ApiParam(value = "This represents the name of the resource to be retrieved.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the resource to be added and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType)
    {
    return delegate.resourceResourceTypeNameGet(name,resourceType);
    }
    @PATCH
    @Path("/{resource-type}/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update existing resource\n", notes = "This API is used to update an existing resource given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNamePatch(@ApiParam(value = "This represents the name of the resource to be added or updated.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the resource to be added and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents the resource that needs to be updated." ,required=true ) ResourceDTO resource)
    {
    return delegate.resourceResourceTypeNamePatch(name,resourceType,resource);
    }
    @POST
    @Path("/{resource-type}/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create the resource\n", notes = "This API is used to store the resource given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNamePost(@ApiParam(value = "This represents the name of the resource to be added.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the resource to be added and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents the resource that needs to be added." ,required=true ) ResourceDTO resource)
    {
    return delegate.resourceResourceTypeNamePost(name,resourceType,resource);
    }
    @PUT
    @Path("/{resource-type}/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add or Replace the resource\n", notes = "This API is used to store or replace the resource given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response resourceResourceTypeNamePut(@ApiParam(value = "This represents the name of the resource to be added or replaced.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the type of the resource to be added and can either be the name or id.",required=true ) @PathParam("resource-type")  String resourceType,
    @ApiParam(value = "This represents the resource that need to be added or replaced." ,required=true ) ResourceDTO resource)
    {
    return delegate.resourceResourceTypeNamePut(name,resourceType,resource);
    }
}

