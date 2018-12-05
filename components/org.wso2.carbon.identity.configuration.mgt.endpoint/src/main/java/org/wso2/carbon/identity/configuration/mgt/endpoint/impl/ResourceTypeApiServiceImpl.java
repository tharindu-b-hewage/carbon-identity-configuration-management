package org.wso2.carbon.identity.configuration.mgt.endpoint.impl;

import org.wso2.carbon.identity.configuration.mgt.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.configuration.mgt.endpoint.ResourceTypeApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeCreateDTO;

import javax.ws.rs.core.Response;

public class ResourceTypeApiServiceImpl extends ResourceTypeApiService {

    @Override
    public Response resourceTypeDelete(String name, String id) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response resourceTypeGet(String name, String id) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response resourceTypePatch(ResourceTypeCreateDTO type) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response resourceTypePost(ResourceTypeCreateDTO type) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response resourceTypePut(ResourceTypeCreateDTO type) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
