package org.wso2.carbon.identity.configuration.mgt.endpoint;

import org.wso2.carbon.identity.configuration.mgt.endpoint.*;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.*;

import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.TenantConfigurationsDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationChangeResponseDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ConfigurationApiService {
    public abstract Response configurationDelete();
    public abstract Response configurationGet();
    public abstract Response configurationNameDelete(String name);
    public abstract Response configurationNameGet(String name);
    public abstract Response configurationNamePatch(String name,ConfigurationDTO configuration);
    public abstract Response configurationNamePost(String name,ConfigurationDTO configuration);
    public abstract Response configurationNamePut(String name,ConfigurationDTO configuration);
    public abstract Response configurationPatch(TenantConfigurationsDTO configurations);
    public abstract Response configurationPost(TenantConfigurationsDTO configurations);
    public abstract Response configurationPut(TenantConfigurationsDTO configurations);
}

