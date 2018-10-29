package org.wso2.carbon.identity.configuration.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ConfigurationChangeResponseDTO  {
  
  
  @NotNull
  private String configurationNameID = null;
  
  @NotNull
  private String state = null;
  
  @NotNull
  private String tenantDomain = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("configurationNameID")
  public String getConfigurationNameID() {
    return configurationNameID;
  }
  public void setConfigurationNameID(String configurationNameID) {
    this.configurationNameID = configurationNameID;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("state")
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("tenantDomain")
  public String getTenantDomain() {
    return tenantDomain;
  }
  public void setTenantDomain(String tenantDomain) {
    this.tenantDomain = tenantDomain;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurationChangeResponseDTO {\n");
    
    sb.append("  configurationNameID: ").append(configurationNameID).append("\n");
    sb.append("  state: ").append(state).append("\n");
    sb.append("  tenantDomain: ").append(tenantDomain).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
