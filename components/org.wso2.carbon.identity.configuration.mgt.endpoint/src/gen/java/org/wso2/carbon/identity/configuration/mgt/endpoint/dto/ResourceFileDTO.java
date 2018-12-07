package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
public class ResourceFileDTO {

    private String value = null;

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("value")
    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceFileDTO {\n");

        sb.append("  value: ").append(value).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
