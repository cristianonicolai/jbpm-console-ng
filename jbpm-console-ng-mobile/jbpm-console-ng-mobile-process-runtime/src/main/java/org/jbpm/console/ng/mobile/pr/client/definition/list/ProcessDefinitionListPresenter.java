/*
 * Copyright 2014 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.console.ng.mobile.pr.client.definition.list;

import com.google.inject.Inject;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.base.HasRefresh;
import com.googlecode.mgwt.ui.client.widget.base.PullArrowWidget;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel;
import java.util.List;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jbpm.console.ng.bd.service.DataServiceEntryPoint;
import org.jbpm.console.ng.mobile.core.client.MGWTUberView;
import org.jbpm.console.ng.pr.model.ProcessSummary;


/**
 *
 * @author livthomas
 */
public class ProcessDefinitionListPresenter {
    
    public interface ProcessDefinitionListView extends MGWTUberView<ProcessDefinitionListPresenter> {

        HasRefresh getPullPanel();

        void setHeaderPullHandler(PullPanel.Pullhandler pullHandler);

        PullArrowWidget getPullHeader();
        
        void render(List<ProcessSummary> tasks);
        
        HasTapHandlers getBackButton();
        
    }

    @Inject
    private Caller<DataServiceEntryPoint> dataServices;
    
    @Inject
    private ProcessDefinitionListView view;
    
    private List<ProcessSummary> definitionsList;

    public void refresh() {
        dataServices.call(new RemoteCallback<List<ProcessSummary>>() {
            @Override
            public void callback(List<ProcessSummary> definitions) {
                definitionsList = definitions;
                view.render(definitionsList);
            }
        } ).getProcesses();
    }

}
