/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.vertx.eventbus;

import io.vertx.core.Vertx;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.ToDefinition;
import org.apache.camel.spi.ProcessorFactory;
import org.apache.camel.spi.RouteContext;

public class VertxProcessorFactory implements ProcessorFactory {

    private final Vertx vertx;

    public VertxProcessorFactory(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public Processor createChildProcessor(RouteContext routeContext, ProcessorDefinition<?> def, boolean mandatory) throws Exception {
        return null;
    }

    @Override
    public Processor createProcessor(RouteContext routeContext, ProcessorDefinition<?> def) throws Exception {
        String id = def.idOrCreate(routeContext.getCamelContext().getNodeIdFactory());

        if (def instanceof ToDefinition) {
            String uri = ((ToDefinition) def).getEndpointUri();
            return new VertxSendToProcessor(vertx, id, uri);
        }

        throw new UnsupportedOperationException("EIP not supported yet");
    }
}
