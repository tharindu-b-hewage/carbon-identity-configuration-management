package org.wso2.carbon.identity.configuration.mgt.core.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementRuntimeException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementServerException;

import java.util.UUID;

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

    public static ConfigurationManagementClientException handleClientException(ConfigurationConstants.ErrorMessages error,
                                                                               String data, Throwable e) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }

        return new ConfigurationManagementClientException(message, error.getCode(), e);
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

    public static ConfigurationManagementServerException handleServerException(ConfigurationConstants.ErrorMessages error,
                                                                               String data, Throwable e) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return new ConfigurationManagementServerException(message, error.getCode(), e);
    }

    /**
     * This method can be used to generate a ConfigurationManagementRuntimeException from
     * ConfigurationConstants.ErrorMessages object when an exception is thrown.
     *
     * @param error ConfigurationConstants.ErrorMessages.
     * @param data  data to replace if message needs to be replaced.
     * @param e     Parent exception.
     * @return ConsentManagementRuntimeException
     */
    public static ConfigurationManagementRuntimeException handleRuntimeException(ConfigurationConstants.ErrorMessages error,
                                                                                 String data, Throwable e) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return new ConfigurationManagementRuntimeException(message, error.getCode(), e);
    }

    /**
     * This method can be used to generate a ConfigurationManagementRuntimeException from ConfigurationConstants.ErrorMessages
     * object when an exception is thrown.
     *
     * @param error ConfigurationConstants.ErrorMessages.
     * @param data  data to replace if message needs to be replaced.
     * @return ConsentManagementRuntimeException
     */
    public static ConfigurationManagementRuntimeException handleRuntimeException(ConfigurationConstants.ErrorMessages error,
                                                                           String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return new ConfigurationManagementRuntimeException(message, error.getCode());
    }

    public static String generateUniqueID() {

        return UUID.randomUUID().toString();
    }
}
