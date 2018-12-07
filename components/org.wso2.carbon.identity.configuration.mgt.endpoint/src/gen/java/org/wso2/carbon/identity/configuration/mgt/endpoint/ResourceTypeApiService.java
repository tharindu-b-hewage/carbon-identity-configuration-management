package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeCreateDTO;

import javax.ws.rs.core.Response;

public abstract class ResourceTypeApiService {

    public abstract Response resourceTypeDelete(String name, String id);

    public abstract Response resourceTypeGet(String name, String id);

    public abstract Response resourceTypePatch(ResourceTypeCreateDTO type);

    public abstract Response resourceTypePost(ResourceTypeCreateDTO type);

    public abstract Response resourceTypePut(ResourceTypeCreateDTO type);
}

