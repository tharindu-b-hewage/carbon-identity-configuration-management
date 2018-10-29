package org.wso2.carbon.identity.configuration.mgt.core.util;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;

public class ConsentUtils {

    /**
     * This method can be used to generate a ConsentManagementClientException from ConsentConstants.ErrorMessages
     * object when no exception is thrown.
     *
     * @param error ConsentConstants.ErrorMessages.
     * @param data  data to replace if message needs to be replaced.
     * @return ConsentManagementClientException.
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
}
