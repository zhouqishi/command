package com.stone.command.invoke;

import com.stone.command.enums.CommandEnum;
import org.springframework.stereotype.Component;

@Component
public class ErrorInvoker implements Invoker<Void, Void> {

    @Override
    public CommandEnum getCommand() {
        return CommandEnum.ERROR;
    }

    @Override
    public void invoke(Void req, Void result) {
        System.out.println("this is command error ");
        throw new RuntimeException("this is command error");
    }

}
