package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "")
public class ResourceAddDTO {

    private List<AttributeDTO> attributes = new ArrayList<AttributeDTO>();

    private ResourceFileDTO file = null;

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
        sb.append("class ResourceAddDTO {\n");

        sb.append("  attributes: ").append(attributes).append("\n");
        sb.append("  file: ").append(file).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
