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

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;

import javax.ws.rs.core.Response;

public abstract class ConfigurationApiService {

    public abstract Response configurationDelete();

    public abstract Response configurationGet();

    public abstract Response configurationNameDelete(String name);

    public abstract Response configurationNameGet(String name);

    public abstract Response configurationNamePatch(String name, TenantConfigurationsDTO configurationsties);

    public abstract Response configurationNamePost(String name, ConfigurationDTO configurations);

    public abstract Response configurationNamePut(String name, ConfigurationDTO configurations);

    public abstract Response configurationPatch(TenantConfigurationsDTO configurations);

    public abstract Response configurationPost(TenantConfigurationsDTO configurations);

    public abstract Response configurationPut(TenantConfigurationsDTO configurations);
}

