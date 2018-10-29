/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.configuration.mgt.core.exception;

/**
 * Base exception for configuration management feature.
 */
public class ConfigurationManagementException extends Exception {

    // private static final long serialVersionUID = 2806215535431246551L; todo: Find a suitable serialVersionUID
    private String errorCode;

    public ConfigurationManagementException() {

        super();
    }

    public ConfigurationManagementException(String message, String errorCode) {

        super(message);
        this.errorCode = errorCode;
    }

    public ConfigurationManagementException(String message, String errorCode, Throwable cause) {

        super(message, cause);
        this.errorCode = errorCode;
    }

    public ConfigurationManagementException(Throwable cause) {

        super(cause);
    }

    public String getErrorCode() {

        return errorCode;
    }

    protected void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }
}
