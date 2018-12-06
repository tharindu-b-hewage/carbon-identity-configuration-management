package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "")
public class AttributeValueDTO {

    @NotNull
    private String value = null;

    /**
     **/
    @ApiModelProperty(required = true, value = "")
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
        sb.append("class AttributeValueDTO {\n");

        sb.append("  value: ").append(value).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}