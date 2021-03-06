/*
 * Copyright (c) 2005 - 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.wso2.siddhi.core.query.output.ratelimit;


import org.apache.log4j.Logger;
import org.wso2.siddhi.core.event.ComplexEvent;
import org.wso2.siddhi.core.event.ComplexEventChunk;

public class PassThroughOutputRateLimiter extends OutputRateLimiter {
    private static final Logger log = Logger.getLogger(PassThroughOutputRateLimiter.class);
    private String id;
    private ComplexEventChunk<ComplexEvent> eventChunk;

    public PassThroughOutputRateLimiter(String id) {
        this.id = id;
        eventChunk = new ComplexEventChunk<ComplexEvent>();
    }

    public PassThroughOutputRateLimiter clone(String key) {
        return new PassThroughOutputRateLimiter(id + key);
    }

    @Override
    public void process(ComplexEventChunk complexEventChunk) {
        sendToCallBacks(eventChunk);
        eventChunk.clear();
    }

    @Override
    public void add(ComplexEvent complexEvent) {
        eventChunk.add(complexEvent);
    }

    @Override
    public void start() {
        //Nothing to start
    }

    @Override
    public void stop() {
        //Nothing to stop
    }

    @Override
    public Object[] currentState() {
        return new Object[]{eventChunk};
    }

    @Override
    public void restoreState(Object[] state) {
        eventChunk = (ComplexEventChunk<ComplexEvent>) state[0];
    }

}
