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

package org.jbpm.workbench.cm.client.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.interceptor.InterceptsRemoteCall;
import org.jboss.errai.enterprise.client.jaxrs.api.interceptor.RestCallContext;
import org.jboss.errai.enterprise.client.jaxrs.api.interceptor.RestClientInterceptor;
import org.jbpm.workbench.cm.service.CaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@InterceptsRemoteCall(CaseResource.class)
@JsType(namespace = "org.jbpm.wb", name = "KieServer")
public class KieServerInterceptor implements RestClientInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KieServerInterceptor.class);
    private static final Map<String, String> HEADERS = new HashMap<>();

    public static void setHttpHeader(final String name, final String value) {
        LOGGER.info("Adding HTTP REST header name: {}, value: {}", name, value);
        HEADERS.put(name, value);
    }

    @Override
    @JsIgnore
    public void aroundInvoke(final RestCallContext restCallContext) {
        GWT.log("around invoke");
        HEADERS.entrySet().forEach(e -> restCallContext.getRequestBuilder().setHeader(e.getKey(), e.getValue()));
        restCallContext.proceed();
    }
}
