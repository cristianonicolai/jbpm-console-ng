/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.jbpm.console.ng.bd.service;

import java.util.Set;

import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.model.DeploymentUnit;

public interface AdministrationService {

    public void bootstrapRepository(String ou, String repoAlias, String repoUrl, String userName, String password);

    public void bootstrapConfig();

    public void bootstrapDeployments();

    public boolean getBootstrapDeploymentsDone();

    public Set<DeploymentUnit> produceDeploymentUnits();

    public DeploymentService getDeploymentService();

    public void bootstrapProject(String repoAlias, String group, String artifact, String version);

}