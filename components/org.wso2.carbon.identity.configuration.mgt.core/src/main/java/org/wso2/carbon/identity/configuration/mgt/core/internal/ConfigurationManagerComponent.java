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

package org.wso2.carbon.identity.configuration.mgt.core.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManagerImpl;
import org.wso2.carbon.identity.configuration.mgt.core.dao.AttributeDAO;
import org.wso2.carbon.identity.configuration.mgt.core.dao.impl.AttributeDAOImpl;

/**
 * OSGi declarative services component which handles registration and un-registration of configuration management service.
 */
@Component(
        name = "carbon.configuration.mgt.component",
        immediate = true
)
public class ConfigurationManagerComponent {

    private static final Log log = LogFactory.getLog(ConfigurationManager.class);
    private AttributeDAO attributeDAO;

    /**
     * Register ConfigurationManager as an OSGI service.
     *
     * @param componentContext OSGI service component context.
     */
    @Activate
    protected void activate(ComponentContext componentContext) {

        try {
            BundleContext bundleContext = componentContext.getBundleContext();
//            bundleContext.registerService(AttributeDAO.class.getName(), new AttributeDAOImpl(), null);
            bundleContext.registerService(ConfigurationManager.class.getName(), new ConfigurationManagerImpl(),
                    null);
        } catch (Throwable e) {
            log.error("Error while activating ConfigurationManagerComponent.", e);
        }
    }

//    @Reference(
//            name = "attribute.dao",
//            service = AttributeDAO.class,
//            cardinality = ReferenceCardinality.MANDATORY,
//            policy = ReferencePolicy.DYNAMIC,
//            unbind = "unsetAttribute"
//    )
//    protected void setAttribute(AttributeDAO attributeDAO) {
//
//        this.attributeDAO = attributeDAO;
//        if (this.attributeDAO != null) {
//            log.debug("SampleAttributeDAO is registered in ConfigurationManager service.");
//        }
//    }
//
//    protected void unsetAttribute(AttributeDAO attributeDAO) {
//
//        if (log.isDebugEnabled()) {
//            log.debug("SampleAttributeDAO is unregistered in ConfigurationManager service.");
//        }
//        this.attributeDAO = null;
//    }
}
