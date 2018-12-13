package org.wso2.carbon.identity.configuration.mgt.endpoint.util.validator;

import org.apache.cxf.jaxrs.ext.search.visitor.PropertyValidationException;
import org.apache.cxf.jaxrs.ext.search.visitor.PropertyValidator;
import org.wso2.carbon.identity.configuration.mgt.core.model.search.ResourceSearchBean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.BEAN_FIELD_FLAG;

/**
 * This class validates the property name of a given property in the {@link ResourceSearchBean} class.
 */
public class ResourceSearchBeanPropertyValidator<T> implements PropertyValidator<T> {

    private Class<ResourceSearchBean> clazz;

    public ResourceSearchBeanPropertyValidator() {

        this.clazz = ResourceSearchBean.class;
    }

    public void validate(String name, T value) throws PropertyValidationException {

        boolean isNameValid = false;
        for (Field field : clazz.getDeclaredFields()) {
            String flaggedFieldName = BEAN_FIELD_FLAG + field.getName();
            if (flaggedFieldName.equals(name)) {
                isNameValid = true;
                break;
            }
        }
        if (!isNameValid) {
            throw new PropertyValidationException(name, value.toString());
        }
    }
}
