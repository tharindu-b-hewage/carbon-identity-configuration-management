package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

@ApiModel(description = "")
public class ResourceDTO {

    @NotNull
    private String resourceName = null;

    private List<AttributeDTO> attributes = new ArrayList<AttributeDTO>();

    @NotNull
    private ResourceTypeDTO type = null;

    private ResourceFileDTO file = null;

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("resourceName")
    public String getResourceName() {

        return resourceName;
    }

    public void setResourceName(String resourceName) {

        this.resourceName = resourceName;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("attributes")
    public List<AttributeDTO> getAttributes() {

        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {

        this.attributes = attributes;
    }

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("type")
    public ResourceTypeDTO getType() {

        return type;
    }

    public void setType(ResourceTypeDTO type) {

        this.type = type;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("file")
    public ResourceFileDTO getFile() {

        return file;
    }

    public void setFile(ResourceFileDTO file) {

        this.file = file;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceDTO {\n");

        sb.append("  resourceName: ").append(resourceName).append("\n");
        sb.append("  attributes: ").append(attributes).append("\n");
        sb.append("  type: ").append(type).append("\n");
        sb.append("  file: ").append(file).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
