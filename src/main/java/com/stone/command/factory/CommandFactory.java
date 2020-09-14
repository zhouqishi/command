package com.stone.command.factory;

import com.stone.command.dto.CommandContent;
import com.stone.command.invoke.InvokerWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandFactory {
    @Autowired
    private CommandRegisterFactory commandRegisterFactory;

    public void invoke(CommandContent commandContent) {
        // 获取invoker
        InvokerWrapper invokerWrapper = commandRegisterFactory.getProvide(commandContent.getCommand());
        if (invokerWrapper == null) {
            log.error("指令不支持");
            throw new RuntimeException("指令不支持");
        }

        // 校验出入参
        // ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 无法获取动态类的泛型
        if (!invokerWrapper.checkType(commandContent.getParam().getBody(), commandContent.getResult().getBody())) {
            log.error("出入参类型不匹配");
            throw new RuntimeException("出入参类型不匹配");
        }

        // 调用接口, 并捕获异常
        try {
            invokerWrapper.getInvoker().invoke(commandContent.getParam().getBody(), commandContent.getResult().getBody());
        } catch (Throwable t) {
            log.error("指令执行异常：", t);
            commandContent.setThrowable(t);
            commandContent.getResult().failure(t.getMessage());
        }
    }

}
