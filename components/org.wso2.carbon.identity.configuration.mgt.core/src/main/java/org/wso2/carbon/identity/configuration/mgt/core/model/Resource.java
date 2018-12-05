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
 * A model class representing a resource.
 */
public class Resource {

    private String resourceName;
    private ResourceType resourceType;
    private ResourceFile resourceFile;
    private List<Attribute> resourceAttribute;

    /**
     * Initialize a Resource object.
     *
     * @param resourceName         Name of the resource.
     * @param resourceType Type of the resource.
     */
    public Resource(String resourceName, ResourceType resourceType) {

        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType() {

        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {

        this.resourceType = resourceType;
    }

    public ResourceFile getResourceFile() {

        return resourceFile;
    }

    public void setResourceFile(ResourceFile resourceFile) {

        this.resourceFile = resourceFile;
    }

    public List<Attribute> getResourceAttribute() {

        return resourceAttribute;
    }

    public String getResourceName() {

        return resourceName;
    }

    public void setResourceAttribute(List<Attribute> resourceAttribute) {

        this.resourceAttribute = resourceAttribute;
    }

    public void setResourceName(String resourceName) {

        this.resourceName = resourceName;
    }
}
