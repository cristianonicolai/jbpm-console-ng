/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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

package org.jbpm.workbench.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

/**
 * This uses GWT to provide client side compile time resolving of locales. See:
 * http://code.google.com/docreader/#p=google-web-toolkit-doc-1-5&s=google-web- toolkit-doc-1-5&t=DevGuideInternationalization
 * (for more information).
 * <p/>
 * Each method name matches up with a key in Constants.properties (the properties file can still be used on the server). To use
 * this, use <code>GWT.create(Constants.class)</code>.
 */
public interface Constants extends Messages {

    Constants INSTANCE = GWT.create(Constants.class);

    String Tasks_List_Admin();

    String Dashboards();

    String Process_Reports();

    String Task_Reports();

    String Project_Authoring();

    String Authoring();

    String Process_Management();

    String Work();

    String LogOut();

    String Home();

    String Process_Instances_Admin();

    String Deploy();

    String Deployments();

    String newItem();

    String Role();

    String User();

    String Settings();

    String Users();

    String Pagination_For_Tables();

    String Experimental_Grid();

    String Experimental();

    String Asset_Management();

    String DeploymentDescriptor();

    String Groups();

    String Group();

    String Execution_Servers();

    String Case_Management();

    String Cases();

    String Data_Sets();

    String Extensions();

    String Business_Processes();

    String The_jBPM_Cycle();

    String Discover_Text();

    String Discover();

    String Design();

    String Design_Text();

    String Deploy_Text();

    String Work_Text();

    String Dashboards_Text();

    String Improve();

    String Improve_Text();

    String ActionNotImplementedYet();

    String Administration();

    String artifactRepository();

    String repositories();

    String listRepositories();

    String cloneRepository();

    String newRepository();

    String MenuOrganizationalUnits();

    String MenuManageOrganizationalUnits();

    String Repository();

    String logoBannerError();

    String assetSearch();

    String Examples();

    String Messages();
}
