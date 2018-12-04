package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeRequestDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceTypeCreateDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ResourceTypeApiService {
    public abstract Response resourceTypeDelete(ResourceTypeRequestDTO type);
    public abstract Response resourceTypeGet(ResourceTypeRequestDTO type);
    public abstract Response resourceTypePatch(ResourceTypeCreateDTO type);
    public abstract Response resourceTypePost(ResourceTypeCreateDTO type);
    public abstract Response resourceTypePut(ResourceTypeCreateDTO type);
}

