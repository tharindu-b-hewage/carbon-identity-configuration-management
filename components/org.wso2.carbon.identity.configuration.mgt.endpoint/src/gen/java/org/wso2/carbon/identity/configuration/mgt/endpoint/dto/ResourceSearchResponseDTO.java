package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

@ApiModel(description = "")
public class ResourceSearchResponseDTO {

    @NotNull
    private List<ResourceSearchResponseElementDTO> resources = new ArrayList<ResourceSearchResponseElementDTO>();

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("resources")
    public List<ResourceSearchResponseElementDTO> getResources() {

        return resources;
    }

    public void setResources(List<ResourceSearchResponseElementDTO> resources) {

        this.resources = resources;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceSearchResponseDTO {\n");

        sb.append("  resources: ").append(resources).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
