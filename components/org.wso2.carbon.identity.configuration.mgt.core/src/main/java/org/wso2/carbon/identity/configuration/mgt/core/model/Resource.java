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

    private String tenantDomain;
    private String resourceName;
    private String resourceType;
    private String lastModified;
    private ResourceFile file;
    private List<Attribute> attribute;

    /**
     * Initialize a Resource object.
     *
     * @param resourceName Name of the resource.
     * @param resourceType Type of the resource.
     */
    public Resource(String resourceName, String resourceType) {

        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    public String getTenantDomain() {

        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {

        this.tenantDomain = tenantDomain;
    }

    public String getLastModified() {

        return lastModified;
    }

    public void setLastModified(String lastModified) {

        this.lastModified = lastModified;
    }

    public String getResourceType() {

        return resourceType;
    }

    public void setResourceType(String resourceType) {

        this.resourceType = resourceType;
    }

    public ResourceFile getFile() {

        return file;
    }

    public void setFile(ResourceFile file) {

        this.file = file;
    }

    public List<Attribute> getAttribute() {

        return attribute;
    }

    public String getResourceName() {

        return resourceName;
    }

    public void setAttribute(List<Attribute> attribute) {

        this.attribute = attribute;
    }

    public void setResourceName(String resourceName) {

        this.resourceName = resourceName;
    }
}
