package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceSearchResponseDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ResourceApiService {
    public abstract Response resourceGet(String name,List<String> resourceName,List<String> tenant,List<String> type);
    public abstract Response resourceNameDelete(String name);
    public abstract Response resourceNameGet(String name,List<String> tenant,List<String> type);
    public abstract Response resourceNamePatch(String name,ResourceDTO resource);
    public abstract Response resourceNamePost(String name,ResourceDTO resource);
    public abstract Response resourceNamePut(String name,ResourceDTO resource);
}

