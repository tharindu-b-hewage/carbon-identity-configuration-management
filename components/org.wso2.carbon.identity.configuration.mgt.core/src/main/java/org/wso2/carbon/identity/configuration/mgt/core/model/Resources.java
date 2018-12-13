package org.wso2.carbon.identity.configuration.mgt.core.model;

import java.util.List;

public class Resources {

    private List<Resource> resources;

    public Resources(List<Resource> resources) {

        this.resources = resources;
    }

    public Resources() {

    }

    public List<Resource> getResources() {

        return resources;
    }

    public void setResources(List<Resource> resources) {

        this.resources = resources;
    }
}
