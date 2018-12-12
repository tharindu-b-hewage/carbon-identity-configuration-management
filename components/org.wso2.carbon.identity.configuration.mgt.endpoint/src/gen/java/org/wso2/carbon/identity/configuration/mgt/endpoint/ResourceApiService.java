package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourcesDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceAddDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ResourceApiService {
    public abstract Response resourceGet();
    public abstract Response resourceResourceTypePatch(String resourceType,ResourceAddDTO resource);
    public abstract Response resourceResourceTypePost(String resourceType,ResourceAddDTO resource);
    public abstract Response resourceResourceTypePut(String resourceType,ResourceAddDTO resource);
    public abstract Response resourceResourceTypeResourceNameAttributeKeyDelete(String resourceName,String resourceType,String attributeKey);
    public abstract Response resourceResourceTypeResourceNameAttributeKeyGet(String resourceName,String resourceType,String attributeKey);
    public abstract Response resourceResourceTypeResourceNameDelete(String resourceName,String resourceType);
    public abstract Response resourceResourceTypeResourceNameGet(String resourceName,String resourceType);
    public abstract Response resourceResourceTypeResourceNamePatch(String resourceName,String resourceType,AttributeDTO attribute);
    public abstract Response resourceResourceTypeResourceNamePost(String resourceName,String resourceType,AttributeDTO attribute);
    public abstract Response resourceResourceTypeResourceNamePut(String resourceName,String resourceType,AttributeDTO attribute);
    public abstract Response resourceResourceTypeResourceTypeNameGet(String resourceTypeName);
}

