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

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import org.jboss.errai.common.client.util.Base64Util;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.jbpm.workbench.cm.client.util.KieServerInterceptor;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;

import static org.jbpm.workbench.cm.client.resources.i18n.Constants.MILESTONES;
import static org.jbpm.workbench.cm.client.util.AbstractCaseInstancePresenter.PARAMETER_CASE_ID;
import static org.jbpm.workbench.cm.client.util.AbstractCaseInstancePresenter.PARAMETER_CONTAINER_ID;

@Dependent
@WorkbenchScreen(identifier = CaseMilestoneListPresenter.SCREEN_ID)
public class CaseMilestoneListPresenter {

    public static final String SCREEN_ID = "Case Milestone List";

    @Inject
    protected TranslationService translationService;

//    @Inject
    protected CaseMilestoneJS view;

    @WorkbenchPartTitle
    public String getTitle() {
        return translationService.format(MILESTONES);
    }

    @WorkbenchPartView
    public CaseMilestoneJS getView() {
        GWT.log("getview: " + view);
        return view;
    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
        final byte[] auth = "admin:admin".getBytes();
        KieServerInterceptor.setHttpHeader("Authorization", "Basic " + Base64Util.encode(auth, 0, auth.length));
        KieServerInterceptor.setHttpHeader("Accept", "application/json");
        KieServerInterceptor.setHttpHeader("Content-Type" , "application/json");

        String caseId = place.getParameter(PARAMETER_CASE_ID, null);
//        this.serverTemplateId = place.getParameter(PARAMETER_SERVER_TEMPLATE_ID, null);
        String containerId = place.getParameter(PARAMETER_CONTAINER_ID, null);
        final String kieServerURL = "http://localhost:8230/kie-server/services/rest/server";
        view = CaseMilestoneJS.create(kieServerURL, containerId, caseId, null);
        GWT.log("view: " + view);
//        view.caseId = caseId;
//        view.containerId = containerId;
        view.refresh(null);
    }

//    @Override
//    protected void clearCaseInstance() {
//        view.removeAllMilestones();
//    }

//    @Override
//    protected void loadCaseInstance(final CaseInstanceSummary cis) {
//        refreshData(caseId);
//    }

//    protected void searchCaseMilestones() {
//        refreshData(caseId);
//    }

//    protected void refreshData(String caseId) {
//        caseService.call((List<CaseMilestoneSummary> milestones) -> {
//            view.setCaseMilestoneList(milestones);
//        }).getCaseMilestones(containerId, caseId, view.getCaseMilestoneSearchRequest());
//    }

}