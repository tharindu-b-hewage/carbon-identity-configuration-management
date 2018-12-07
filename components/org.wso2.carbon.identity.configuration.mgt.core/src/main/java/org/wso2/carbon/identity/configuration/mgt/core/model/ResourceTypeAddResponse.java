package org.wso2.carbon.identity.configuration.mgt.core.model;

public class ResourceTypeAddResponse {

    private String id;

    private String name;

    public ResourceTypeAddResponse(String id, String name) {

        this.id = id;
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
