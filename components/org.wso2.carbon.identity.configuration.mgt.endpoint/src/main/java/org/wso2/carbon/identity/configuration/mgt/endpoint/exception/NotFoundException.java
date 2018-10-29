package org.wso2.carbon.identity.configuration.mgt.endpoint.exception;

import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;
import org.wso2.carbon.identity.configuration.mgt.endpoint.dto.ErrorDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {

    private String message;

    public NotFoundException(ErrorDTO errorDTO) {

        super(Response.status(Response.Status.NOT_FOUND)
                .entity(errorDTO)
                .header(ConfigurationConstants.HEADER_CONTENT_TYPE, ConfigurationConstants.DEFAULT_RESPONSE_CONTENT_TYPE)
                .build());
        message = errorDTO.getDescription();
    }

    public NotFoundException() {

        super(Response.Status.NOT_FOUND);
    }

    @Override
    public String getMessage() {

        return message;
    }
}
