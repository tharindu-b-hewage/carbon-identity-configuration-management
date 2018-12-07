package org.wso2.carbon.identity.configuration.mgt.core.model;

import java.util.List;

public class TenantResponse {

    private List<Tenant> tenants;

    public List<Tenant> getTenants() {

        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {

        this.tenants = tenants;
    }
}
