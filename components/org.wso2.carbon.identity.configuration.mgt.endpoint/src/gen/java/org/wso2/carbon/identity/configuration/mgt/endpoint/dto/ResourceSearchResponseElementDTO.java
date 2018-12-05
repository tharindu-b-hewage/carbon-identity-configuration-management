package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "")
public class ResourceSearchResponseElementDTO {

    @NotNull
    private ResourceDTO resource = null;

    @NotNull
    private String tenantId = null;

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("resource")
    public ResourceDTO getResource() {

        return resource;
    }

    public void setResource(ResourceDTO resource) {

        this.resource = resource;
    }

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("tenantId")
    public String getTenantId() {

        return tenantId;
    }

    public void setTenantId(String tenantId) {

        this.tenantId = tenantId;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceSearchResponseElementDTO {\n");

        sb.append("  resource: ").append(resource).append("\n");
        sb.append("  tenantId: ").append(tenantId).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
