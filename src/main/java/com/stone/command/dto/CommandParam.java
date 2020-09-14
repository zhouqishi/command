package com.stone.command.dto;

import lombok.Data;

@Data
public class CommandParam<P> {

    private P body;

    public static <P> CommandParam<P> buildCommandParam(P body) {
        CommandParam<P> param = new CommandParam<>();
        param.setBody(body);
        return param;
    }

}
