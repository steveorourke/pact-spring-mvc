/*
 * #%L
 * pact-spring-mvc
 * %%
 * Copyright (C) 2016 - 2019 Tyro Payments Pty Ltd
 * %%
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
 * #L%
 */
package com.tyro.oss.pact.spring.pact.examples;

import com.tyro.oss.pact.rest.RestRequestDescriptor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class RestRequestInvoker {

    private RestTemplate restTemplate;

    public RestRequestInvoker(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public <T> T forDescriptor(RestRequestDescriptor<T> descriptor) {
        final HttpEntity<?> requestEntity = new HttpEntity<>(descriptor.getRequest());

        if (descriptor.hasParameterizedResponseType()) {
            return restTemplate.exchange(
                    descriptor.getUrl(),
                    descriptor.getMethod(),
                    requestEntity,
                    descriptor.getParameterizedResponseType()).getBody();
        } else {
            return restTemplate.exchange(
                    descriptor.getUrl(),
                    descriptor.getMethod(),
                    requestEntity,
                    descriptor.getResponseType()).getBody();
        }
    }
}
