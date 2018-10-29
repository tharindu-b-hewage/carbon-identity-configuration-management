package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ConfigurationDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class TenantConfigurationsDTO  {
  
  
  @NotNull
  private List<ConfigurationDTO> configurations = new ArrayList<ConfigurationDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("configurations")
  public List<ConfigurationDTO> getConfigurations() {
    return configurations;
  }
  public void setConfigurations(List<ConfigurationDTO> configurations) {
    this.configurations = configurations;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class TenantConfigurationsDTO {\n");
    
    sb.append("  configurations: ").append(configurations).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
