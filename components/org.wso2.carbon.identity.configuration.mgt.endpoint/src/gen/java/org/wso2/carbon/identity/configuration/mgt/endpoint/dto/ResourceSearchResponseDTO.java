package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ResourceSearchResponseElementDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ResourceSearchResponseDTO  {
  
  
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
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceSearchResponseDTO {\n");
    
    sb.append("  resources: ").append(resources).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
