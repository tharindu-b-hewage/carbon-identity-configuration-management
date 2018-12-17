/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.identity.configuration.mgt.endpoint.util.validator;

import org.apache.cxf.jaxrs.ext.search.visitor.PropertyValidationException;
import org.apache.cxf.jaxrs.ext.search.visitor.PropertyValidator;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceSearchBean;

import java.lang.reflect.Field;

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
