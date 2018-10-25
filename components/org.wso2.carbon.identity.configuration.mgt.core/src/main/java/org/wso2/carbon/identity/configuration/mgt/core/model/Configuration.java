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

package org.wso2.carbon.identity.configuration.mgt.core.model;

import java.util.List;

/**
 * A model class representing a configuration.
 * Ex: Email configuration for a tenant
 */
public class Configuration {

    private String name;
    private List<Attribute> attributes;

    /**
     * Initialize a Configuration object.
     * @param name Name of the configuration.
     * @param attributes Collection of attributes in the configuration.
     */
    public Configuration(String name, List<Attribute> attributes) {

        this.name = name;
        this.attributes = attributes;
    }

    public List<Attribute> getAttributes() {

        return attributes;
    }

    public String getName() {

        return name;
    }

    public void setAttributes(List<Attribute> attributes) {

        this.attributes = attributes;
    }

    public void setName(String name) {

        this.name = name;
    }
}
