/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.jbpm.console.ng.gc.forms.client.display.views;

import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.jbpm.console.ng.gc.forms.client.display.GenericFormDisplayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class EmbeddedFormDisplayViewTest {

    @Mock
    private GenericFormDisplayer displayerMock;

    @Mock
    private VerticalPanel formContainer;

    @InjectMocks
    protected EmbeddedFormDisplayView view;

    @Test
    public void displayPanelCreationTest() {
        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                if (!(invocationOnMock.getArguments()[0] instanceof ScrollPanel) &&
                        !(invocationOnMock.getArguments()[0] instanceof VerticalPanel)) {
                    fail();
                }
                return null;
            }
        }).when(formContainer).add(any(Widget.class));

        view.display(displayerMock);

        verify(formContainer).clear();
        verify(formContainer, times(2)).add(any(Widget.class));

        verify(displayerMock).getContainer();
        verify(displayerMock).getFooter();
    }

}
