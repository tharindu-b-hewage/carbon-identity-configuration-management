package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "")
public class ResourceTypeAddResponseDTO {

    @NotNull
    private String id = null;

    private String name = null;

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("id")
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("name")
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceTypeAddResponseDTO {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
