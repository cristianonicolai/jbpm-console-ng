/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.workbench.cm.client.milestones;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.IsElement;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.enterprise.client.jaxrs.MarshallingWrapper;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.container.IOC;
import org.jbpm.workbench.cm.model.CaseMilestoneList;
import org.jbpm.workbench.cm.model.CaseMilestoneSummary;
import org.jbpm.workbench.cm.service.CaseResource;
import org.jbpm.workbench.cm.client.util.JsCallbackFunction;
import org.slf4j.Logger;

@JsType(namespace = "org.jbpm.wb", name = "CaseMilestone")
@Dependent
public class CaseMilestoneJS implements IsElement {

    private String containerId;
    private String caseId;
    private String kieServerURL;

    @Inject
    @JsIgnore
    private CaseMilestoneListView view;

    @Inject
    @JsIgnore
    private Logger logger;

    public static CaseMilestoneJS create(final String kieServerURL,
                                         final String containerId,
                                         final String caseId,
                                         final HTMLElement parent) {
        final CaseMilestoneJS caseMilestoneJS = IOC.getBeanManager().lookupBean(CaseMilestoneJS.class).getInstance();
        caseMilestoneJS.kieServerURL = kieServerURL;
        caseMilestoneJS.containerId = containerId;
        caseMilestoneJS.caseId = caseId;
        if(parent!=null) {
            parent.appendChild(caseMilestoneJS.view.getElement());
        }
        return caseMilestoneJS;
    }

    @Override
    @JsIgnore
    public HTMLElement getElement() {
        return view.getElement();
    }

    public void refresh(final JsCallbackFunction callback) {
        RestClient.setJacksonMarshallingActive(true);
        view.removeAllMilestones();
        RestClient.create(CaseResource.class, kieServerURL, (Response r) -> {
            if (r.getStatusCode() == 200) {
                final CaseMilestoneList m = MarshallingWrapper.fromJSON(r.getText(), CaseMilestoneList.class);
                view.setCaseMilestoneList(Arrays.asList(m.getMilestones()));
                if (callback != null) {
                    callback.execute();
                }
            }
        }, (Request message, Throwable throwable) -> {
            GWT.log("error 2: " + throwable.getMessage());
            logger.info("error callback");
            logger.info("error message: " + message);
            logger.error("error message: " + throwable.getMessage(), throwable);
            return false;
        }).getCaseInstanceMilestones(containerId, caseId, false, 0, 10);
    }

    public interface CaseMilestoneListView extends IsElement {

        void removeAllMilestones();

        void setCaseMilestoneList(List<CaseMilestoneSummary> caseMilestoneList);

    }

}