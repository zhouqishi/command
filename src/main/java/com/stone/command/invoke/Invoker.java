package com.stone.command.invoke;

import com.stone.command.enums.CommandEnum;

public interface Invoker<P, R> {

    CommandEnum getCommand();

    void invoke(P param, R result);

}
