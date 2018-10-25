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

package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.wso2.carbon.identity.configuration.mgt.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ConfigurationApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationDTO;
import static org.wso2.carbon.identity.configuration.mgt.endpoint.util.ConfigurationEndpointUtils.getConfigurationManager;

public class ConfigurationApiServiceImpl extends ConfigurationApiService {

    @Override
    public Response configurationDelete() {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationGet() {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationNameDelete(String name) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationNameGet(String name) {

        ConfigurationManager configurationManager = getConfigurationManager();
        ConfigurationDTO configurationDTO = getConfigurationDTO(configurationManager.getConfiguration());
        return Response.ok().entity(configurationDTO).build();
    }

    @Override
    public Response configurationNamePatch(String name, TenantConfigurationsDTO configurationsties) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationNamePost(String name, ConfigurationDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationNamePut(String name, ConfigurationDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationPatch(TenantConfigurationsDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationPost(TenantConfigurationsDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response configurationPut(TenantConfigurationsDTO configurations) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
