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
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

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

        List<AttributeDTO> attributeDTOS = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            attributeDTOS.add(new AttributeDTO());
        }
        attributeDTOS.get(0).setKey("mail.smtp.from");
        attributeDTOS.get(0).setValue("abc@gmail.com");
        attributeDTOS.get(1).setKey("mail.smtp.host");
        attributeDTOS.get(1).setValue("smtp.gmail.com");

        ConfigurationDTO configurationDTO = new ConfigurationDTO();
        configurationDTO.setName("email");
        configurationDTO.setAttributes(attributeDTOS);

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
