package org.wso2.carbon.identity.configuration.mgt.core.exception;

public class ConfigurationManagementServerException extends ConfigurationManagementException{

    public ConfigurationManagementServerException() {

        super();
    }

    public ConfigurationManagementServerException(String message, String errorCode) {

        super(message, errorCode);
    }

    public ConfigurationManagementServerException(String message, String errorCode, Throwable cause) {

        super(message, errorCode, cause);
    }

    public ConfigurationManagementServerException(Throwable cause) {

        super(cause);
    }
}
