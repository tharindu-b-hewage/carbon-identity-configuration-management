package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.AttributeDTO;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceFileDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ResourceDTO  {
  
  
  @NotNull
  private String resourceName = null;
  
  @NotNull
  private String resourceType = null;
  
  @NotNull
  private String lastModified = null;
  
  
  private List<AttributeDTO> attributes = new ArrayList<AttributeDTO>();
  
  
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
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("resourceType")
  public String getResourceType() {
    return resourceType;
  }
  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("lastModified")
  public String getLastModified() {
    return lastModified;
  }
  public void setLastModified(String lastModified) {
    this.lastModified = lastModified;
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
  @ApiModelProperty(value = "")
  @JsonProperty("file")
  public ResourceFileDTO getFile() {
    return file;
  }
  public void setFile(ResourceFileDTO file) {
    this.file = file;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceDTO {\n");
    
    sb.append("  resourceName: ").append(resourceName).append("\n");
    sb.append("  resourceType: ").append(resourceType).append("\n");
    sb.append("  lastModified: ").append(lastModified).append("\n");
    sb.append("  attributes: ").append(attributes).append("\n");
    sb.append("  file: ").append(file).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
