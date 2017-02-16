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

package org.jbpm.workbench.cm.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.jbpm.workbench.cm.service.CaseResource.CASE_URI;

@Path(CASE_URI)
public interface CaseResource {

    String CONTAINER_ID = "id";
    String CASE_ID = "caseId";
    String CASE_DEF_ID = "caseDefId";
    String CASE_URI = "containers/{" + CONTAINER_ID + "}/cases";
    String CASE_MILESTONES_GET_URI = "instances/{" + CASE_ID + "}/milestones";
    String START_CASE_POST_URI = "{" + CASE_DEF_ID +"}/instances";
    String CASE_INSTANCE_GET_URI = "instances/{" + CASE_ID + "}";
    String CASE_COMMENTS_GET_URI = "instances/{" + CASE_ID + "}/comments";

    @GET
    @Path(CASE_MILESTONES_GET_URI)
    @Produces(MediaType.APPLICATION_JSON)
    Response getCaseInstanceMilestones(@PathParam(CONTAINER_ID) String containerId, @PathParam(CASE_ID) String caseId,
                                       @QueryParam("achievedOnly") @DefaultValue("true") boolean achievedOnly,
                                       @QueryParam("page") @DefaultValue("0") Integer page, @QueryParam("pageSize") @DefaultValue("10") Integer pageSize);

    @POST
    @Path(START_CASE_POST_URI)
    @Produces(MediaType.APPLICATION_JSON)
    Response startCase(@PathParam(CONTAINER_ID) String containerId, @PathParam(CASE_DEF_ID) String caseDefId, String payload);

    @GET
    @Path(CASE_INSTANCE_GET_URI)
    @Produces(MediaType.APPLICATION_JSON)
    Response getCaseInstance(@PathParam(CONTAINER_ID) String containerId, @PathParam(CASE_ID) String caseId,
                             @QueryParam("withData") @DefaultValue("false") boolean withData,
                             @QueryParam("withRoles") @DefaultValue("false") boolean withRoles,
                             @QueryParam("withMilestones") @DefaultValue("false") boolean withMilestones,
                             @QueryParam("withStages") @DefaultValue("false") boolean withStages);

    @GET
    @Path(CASE_COMMENTS_GET_URI)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getCaseInstanceComments(@PathParam(CONTAINER_ID) String containerId, @PathParam(CASE_ID) String caseId,
                                     @QueryParam("page") @DefaultValue("0") Integer page, @QueryParam("pageSize") @DefaultValue("10") Integer pageSize);

}
