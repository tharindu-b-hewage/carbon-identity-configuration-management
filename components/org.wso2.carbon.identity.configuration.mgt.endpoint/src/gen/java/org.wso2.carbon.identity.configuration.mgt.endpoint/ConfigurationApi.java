/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.configuration.mgt.endpoint;

import io.swagger.annotations.ApiParam;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.factories.ConfigurationApiServiceFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/configuration")
@Consumes({"application/json"})
@Produces({"application/json"})
@io.swagger.annotations.Api(value = "/configuration", description = "the configuration API")
public class ConfigurationApi {

    private final ConfigurationApiService delegate = ConfigurationApiServiceFactory.getConfigurationApi();

    @DELETE
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Revoke configurations\n", notes = "This API is used to revoke the configurations.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationDelete() {

        return delegate.configurationDelete();
    }

    @GET

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Retrieve configurations\n", notes = "This API is used to retrieve configurations for the tenant domain.\n", response = TenantConfigurationsDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationGet() {

        return delegate.configurationGet();
    }

    @DELETE
    @Path("/{name}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Revoke the configuration\n", notes = "This API is used to revoke a configuration in the tenant domain given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationNameDelete(@ApiParam(value = "This represents the name of the configuration to be retrieved.", required = true) @PathParam("name") String name) {

        return delegate.configurationNameDelete(name);
    }

    @GET
    @Path("/{name}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Retrieve the configuration\n", notes = "This API is used to retrieve a configuration for the tenant domain given by the user.\n", response = ConfigurationDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationNameGet(@ApiParam(value = "This represents the name of the configuration to be retrieved.", required = true) @PathParam("name") String name) {

        return delegate.configurationNameGet(name);
    }

    @PATCH
    @Path("/{name}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Update existing configuration\n", notes = "This API is used to update an existing configuration given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationNamePatch(@ApiParam(value = "This represents the name of the configuration to be added or updated.", required = true) @PathParam("name") String name,
                                           @ApiParam(value = "This represents the configuration that needs to be updated.", required = true) TenantConfigurationsDTO configurationsties) {

        return delegate.configurationNamePatch(name, configurationsties);
    }

    @POST
    @Path("/{name}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Add the configuration\n", notes = "This API is used to store the configuration given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationNamePost(@ApiParam(value = "This represents the name of the configuration to be added.", required = true) @PathParam("name") String name,
                                          @ApiParam(value = "This represents the configuration that needs to be stored.", required = true) ConfigurationDTO configurations) {

        return delegate.configurationNamePost(name, configurations);
    }

    @PUT
    @Path("/{name}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Add or Replace the configuration\n", notes = "This API is used to store or replace the configuration given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationNamePut(@ApiParam(value = "This represents the name of the configuration to be added or replaced.", required = true) @PathParam("name") String name,
                                         @ApiParam(value = "This represents the set of configurations that needs to be stored or replaced.", required = true) ConfigurationDTO configurations) {

        return delegate.configurationNamePut(name, configurations);
    }

    @PATCH

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Update existing configurations\n", notes = "This API is used to update existing configurations given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationPatch(@ApiParam(value = "This represents the set of configurations that needs to be updated.", required = true) TenantConfigurationsDTO configurations) {

        return delegate.configurationPatch(configurations);
    }

    @POST

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Add configurations\n", notes = "This API is used to store configurations given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationPost(@ApiParam(value = "This represents the set of configurations that needs to be stored.", required = true) TenantConfigurationsDTO configurations) {

        return delegate.configurationPost(configurations);
    }

    @PUT

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Add or Replace configurations\n", notes = "This API is used to store or replace configurations given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response"),

            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error")})

    public Response configurationPut(@ApiParam(value = "This represents the set of configurations that needs to be stored or replaced.", required = true) TenantConfigurationsDTO configurations) {

        return delegate.configurationPut(configurations);
    }
}

