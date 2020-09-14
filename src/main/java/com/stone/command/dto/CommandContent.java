package com.stone.command.dto;

import com.stone.command.enums.CommandEnum;
import lombok.Data;

@Data
public class CommandContent<P, R> {

    private CommandEnum command;

    private CommandParam<P> param;

    private CommandResult<R> result;

    private Throwable throwable;

    public static <P, R> CommandContent<P, R>  buildCommandContent(CommandEnum command, CommandParam<P> param, CommandResult<R> result) {
        CommandContent<P, R> commandContent = new CommandContent<>();
        commandContent.setCommand(command);
        commandContent.setParam(param);
        commandContent.setResult(result);
        return commandContent;
    }

}
