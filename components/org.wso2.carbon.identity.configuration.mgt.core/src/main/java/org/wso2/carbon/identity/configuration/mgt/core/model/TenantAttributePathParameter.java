package org.wso2.carbon.identity.configuration.mgt.core.model;

public class TenantAttributePathParameter extends AttributePathParameter {

    private String tenantDomain;

    public String getTenantDomain() {

        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {

        this.tenantDomain = tenantDomain;
    }
}
