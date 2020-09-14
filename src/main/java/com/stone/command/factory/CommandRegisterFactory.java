package com.stone.command.factory;

import com.stone.command.enums.CommandEnum;
import com.stone.command.invoke.Invoker;
import com.stone.command.invoke.InvokerWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class CommandRegisterFactory implements InitializingBean {

    private Map<CommandEnum, InvokerWrapper> invokerMap = new ConcurrentHashMap<>();

    @Autowired
    private List<Invoker> invokers;

    public InvokerWrapper getProvide(CommandEnum commandEnum) {
        InvokerWrapper invokerWrapper = invokerMap.get(commandEnum);
        return invokerWrapper;
    }

    private InvokerWrapper wrapper(Invoker invoker) {
        InvokerWrapper invokerWrapper = new InvokerWrapper(invoker);

        // 设置出入参类型
        Method[] methods = invoker.getClass().getMethods();

        for(Method method : methods) {
            if("invoke".equals(method.getName())
                    && method.getParameterTypes().length == 2) {
                Class<?>[] classes = method.getParameterTypes();
                // 这里过滤掉出入参都是Object
                if (Object.class == classes[0] && Object.class == classes[1]) {
                    continue;
                } else {
                    invokerWrapper.setReqClass(classes[0]);
                    invokerWrapper.setResClass(classes[1]);
                }
            }
        }
        return invokerWrapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (Invoker invoker : invokers) {
            log.info("注册command={}, invoker={}", invoker.getCommand(), invoker.getClass());
            invokerMap.put(invoker.getCommand(), wrapper(invoker));
        }
    }
}
