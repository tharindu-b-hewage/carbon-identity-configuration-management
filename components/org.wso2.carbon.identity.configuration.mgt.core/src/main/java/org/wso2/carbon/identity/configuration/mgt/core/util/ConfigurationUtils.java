package org.wso2.carbon.identity.configuration.mgt.core.util;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementServerException;

public class ConfigurationUtils {

    /**
     * This method can be used to generate a ConfigurationManagementClientException from
     * ConfigurationConstants.ErrorMessages object when no exception is thrown.
     *
     * @param error ConfigurationConstants.ErrorMessages.
     * @param data  data to replace if message needs to be replaced.
     * @return ConfigurationManagementClientException.
     */
    public static ConfigurationManagementClientException handleClientException(ConfigurationConstants.ErrorMessages error,
                                                                               String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }

        return new ConfigurationManagementClientException(message, error.getCode());
    }

    /**
     * This method can be used to generate a ConfigurationManagementServerException from
     * ConfigurationConstants.ErrorMessages object when no exception is thrown.
     *
     * @param error ConfigurationConstants.ErrorMessages.
     * @param data  data to replace if message needs to be replaced.
     * @return ConfigurationManagementServerException.
     */
    public static ConfigurationManagementServerException handleServerException(ConfigurationConstants.ErrorMessages error,
                                                                               String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return new ConfigurationManagementServerException(message, error.getCode());
    }
}
