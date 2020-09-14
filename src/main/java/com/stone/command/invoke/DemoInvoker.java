package com.stone.command.invoke;

import com.stone.command.domian.DemoReq;
import com.stone.command.enums.CommandEnum;
import org.springframework.stereotype.Component;

@Component
public class DemoInvoker implements Invoker<DemoReq, Void> {

    @Override
    public CommandEnum getCommand() {
        return CommandEnum.DEMO;
    }

    @Override
    public void invoke(DemoReq req, Void result) {
        System.out.println("this is command demo ");
    }

}
