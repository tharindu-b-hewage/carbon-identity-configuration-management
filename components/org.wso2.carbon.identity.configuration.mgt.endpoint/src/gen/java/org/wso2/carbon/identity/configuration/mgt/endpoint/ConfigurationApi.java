package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ConfigurationApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.factories.ConfigurationApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationChangeResponseDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/configuration")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/configuration", description = "the configuration API")
public class ConfigurationApi  {

   private final ConfigurationApiService delegate = ConfigurationApiServiceFactory.getConfigurationApi();

    @DELETE
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Revoke configurations\n", notes = "This API is used to revoke the configurations.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationDelete()
    {
    return delegate.configurationDelete();
    }
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve configurations\n", notes = "This API is used to retrieve configurations for the tenant domain.\n", response = TenantConfigurationsDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationGet()
    {
    return delegate.configurationGet();
    }
    @DELETE
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Revoke the configuration\n", notes = "This API is used to revoke a configuration in the tenant domain given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationNameDelete(@ApiParam(value = "This represents the name of the configuration to be retrieved.",required=true ) @PathParam("name")  String name)
    {
    return delegate.configurationNameDelete(name);
    }
    @GET
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve the configuration\n", notes = "This API is used to retrieve a configuration for the tenant domain given by the user.\n", response = ConfigurationDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationNameGet(@ApiParam(value = "This represents the name of the configuration to be retrieved.",required=true ) @PathParam("name")  String name)
    {
    return delegate.configurationNameGet(name);
    }
    @PATCH
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update existing configuration\n", notes = "This API is used to update an existing configuration given by the user.\n", response = ConfigurationChangeResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationNamePatch(@ApiParam(value = "This represents the name of the configuration to be added or updated.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the configuration that needs to be updated." ,required=true ) ConfigurationDTO configuration)
    {
    return delegate.configurationNamePatch(name,configuration);
    }
    @POST
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add the configuration\n", notes = "This API is used to store the configuration given by the user.\n", response = ConfigurationChangeResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationNamePost(@ApiParam(value = "This represents the name of the configuration to be added.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the configuration that needs to be added." ,required=true ) ConfigurationDTO configuration)
    {
    return delegate.configurationNamePost(name,configuration);
    }
    @PUT
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add or Replace the configuration\n", notes = "This API is used to store or replace the configuration given by the user.\n", response = ConfigurationChangeResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationNamePut(@ApiParam(value = "This represents the name of the configuration to be added or replaced.",required=true ) @PathParam("name")  String name,
    @ApiParam(value = "This represents the configuration that need to be added or replaced." ,required=true ) ConfigurationDTO configuration)
    {
    return delegate.configurationNamePut(name,configuration);
    }
    @PATCH
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update existing configurations\n", notes = "This API is used to update existing configurations given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationPatch(@ApiParam(value = "This represents the set of configurations that needs to be updated." ,required=true ) TenantConfigurationsDTO configurations)
    {
    return delegate.configurationPatch(configurations);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add configurations\n", notes = "This API is used to store configurations given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationPost(@ApiParam(value = "This represents the set of configurations that needs to be stored." ,required=true ) TenantConfigurationsDTO configurations)
    {
    return delegate.configurationPost(configurations);
    }
    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add or Replace configurations\n", notes = "This API is used to store or replace configurations given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response configurationPut(@ApiParam(value = "This represents the set of configurations that needs to be stored or replaced." ,required=true ) TenantConfigurationsDTO configurations)
    {
    return delegate.configurationPut(configurations);
    }
}

