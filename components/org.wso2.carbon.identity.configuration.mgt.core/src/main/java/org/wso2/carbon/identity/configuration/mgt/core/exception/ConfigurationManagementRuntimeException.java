package org.wso2.carbon.identity.configuration.mgt.core.exception;

/**
 * This class is an {@link RuntimeException}.
 */
public class ConfigurationManagementRuntimeException extends RuntimeException {

    private String erroCode;

    public ConfigurationManagementRuntimeException() {

        super();
    }

    public ConfigurationManagementRuntimeException(String message, String erroCode) {

        super(message);
        this.erroCode = erroCode;
    }

    public ConfigurationManagementRuntimeException(String message, String erroCode, Throwable cause) {

        super(message, cause);
        this.erroCode = erroCode;
    }

    public ConfigurationManagementRuntimeException(Throwable cause) {

        super(cause);
    }

    protected String getErroCode() {

        return erroCode;
    }

    protected void setErroCode(String erroCode) {

        this.erroCode = erroCode;
    }
}
