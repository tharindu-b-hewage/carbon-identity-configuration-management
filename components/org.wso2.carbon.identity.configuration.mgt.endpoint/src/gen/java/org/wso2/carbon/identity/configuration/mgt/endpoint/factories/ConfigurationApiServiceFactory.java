package org.wso2.carbon.identity.configuration.mgt.endpoint.factories;

import org.wso2.carbon.identity.configuration.mgt.endpoint.ConfigurationApiService;
import org.wso2.carbon.identity.configuration.mgt.endpoint.impl.ConfigurationApiServiceImpl;

public class ConfigurationApiServiceFactory {

   private final static ConfigurationApiService service = new ConfigurationApiServiceImpl();

   public static ConfigurationApiService getConfigurationApi()
   {
      return service;
   }
}
