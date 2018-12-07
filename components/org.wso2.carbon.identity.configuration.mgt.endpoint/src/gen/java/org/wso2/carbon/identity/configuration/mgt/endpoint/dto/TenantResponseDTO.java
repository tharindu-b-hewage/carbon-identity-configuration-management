package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

@ApiModel(description = "")
public class TenantResponseDTO {

    @NotNull
    private List<TenantDTO> tenants = new ArrayList<TenantDTO>();

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("tenants")
    public List<TenantDTO> getTenants() {

        return tenants;
    }

    public void setTenants(List<TenantDTO> tenants) {

        this.tenants = tenants;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class TenantResponseDTO {\n");

        sb.append("  tenants: ").append(tenants).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
