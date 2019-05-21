package com.alibaba.csp.sentinel.adapter.pigeon;

import com.dianping.pigeon.remoting.invoker.domain.InvokerContext;
import com.dianping.pigeon.remoting.provider.domain.ProviderContext;

import java.lang.reflect.Parameter;

public final class PigeonUtils {

    public static String getResourceName(ProviderContext providerContext) {
        StringBuilder buf = new StringBuilder(64);
        buf.append(providerContext.getMethodUri())
            .append("(");
        boolean isFirst = true;
        for (Class<?> clazz : providerContext.getServiceMethod().getMethod().getParameterTypes()) {
            if (!isFirst) {
                buf.append(",");
            }
            buf.append(clazz.getName());
            isFirst = false;
        }
        buf.append(")");
        return buf.toString();
    }

    public static String getResourceName(InvokerContext invokerContext) {
        StringBuilder buf = new StringBuilder(64);
        buf.append(invokerContext.getClient().getConnectInfo().getConnect())
                .append(":")
                .append(invokerContext.getMethodName())
                .append("(");
        boolean isFirst = true;
        for (Class<?> clazz : invokerContext.getParameterTypes()) {
            if (!isFirst) {
                buf.append(",");
            }
            buf.append(clazz.getName());
            isFirst = false;
        }
        buf.append(")");
        return buf.toString();
    }

    public static Parameter[] getMethodArguments(ProviderContext providerContext) {
        return providerContext.getServiceMethod().getMethod().getParameters();
    }

    private PigeonUtils() {}

}
