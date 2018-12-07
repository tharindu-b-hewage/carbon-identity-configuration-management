package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceSearchResponseDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeValueDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ResourceApiService {
    public abstract Response resourceGet();
    public abstract Response resourceResourceTypeNameAttributeDelete(String name,String resourceType,String attribute);
    public abstract Response resourceResourceTypeNameAttributeGet(String name,String resourceType,String attribute);
    public abstract Response resourceResourceTypeNameAttributePatch(String name,String resourceType,String attribute,AttributeValueDTO attributeValue);
    public abstract Response resourceResourceTypeNameAttributePost(String name,String resourceType,String attribute,AttributeValueDTO attributeValue);
    public abstract Response resourceResourceTypeNameAttributePut(String name,String resourceType,String attribute,AttributeValueDTO attributeValue);
    public abstract Response resourceResourceTypeNameDelete(String name,String resourceType);
    public abstract Response resourceResourceTypeNameGet(String name,String resourceType);
    public abstract Response resourceResourceTypeNamePatch(String name,String resourceType,ResourceDTO resource);
    public abstract Response resourceResourceTypeNamePost(String name,String resourceType,ResourceDTO resource);
    public abstract Response resourceResourceTypeNamePut(String name,String resourceType,ResourceDTO resource);
}

