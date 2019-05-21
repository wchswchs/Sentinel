/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
package com.alibaba.csp.sentinel.demo.pigeon;

import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;

/**
 * Provider demo for Pigeon 2.9.7. Please add the following VM arguments:
 * <pre>
 * -Djava.net.preferIPv4Stack=true
 * -Dcsp.sentinel.api.port=8720
 * -Dproject.name=pigeon-provider-demo
 * </pre>
 *
 * @author Jobs Wang
 */
public class FooProviderBootstrap {

    private static final String INTERFACE_RES_KEY = FooService.class.getName();
    private static final String RES_KEY = INTERFACE_RES_KEY + ":sayHello(java.lang.String)";

    public static void main(String[] args) {
        // Users don't need to manually call this method.
        // Only for eager initialization.
        InitExecutor.doInit();

        initFlowRule();

        ClassPathXmlApplicationContext providerContext = new ClassPathXmlApplicationContext("spring/provider.xml");
        providerContext.start();

        System.out.println("Service provider is ready");
    }

    private static void initFlowRule() {
        FlowRule flowRule = new FlowRule(RES_KEY)
            .setCount(10)
            .setGrade(RuleConstant.FLOW_GRADE_QPS);
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }

}
