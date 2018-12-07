package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
public class TenantDTO {

    private String tenantDomain = null;

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("tenantDomain")
    public String getTenantDomain() {

        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {

        this.tenantDomain = tenantDomain;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class TenantDTO {\n");

        sb.append("  tenantDomain: ").append(tenantDomain).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
