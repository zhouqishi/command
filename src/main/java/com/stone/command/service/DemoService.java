package com.stone.command.service;

import com.stone.command.domian.DemoReq;
import com.stone.command.dto.CommandContent;
import com.stone.command.dto.CommandParam;
import com.stone.command.dto.CommandResult;
import com.stone.command.enums.CommandEnum;
import com.stone.command.factory.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoService {

    @Autowired
    private CommandFactory commandFactory;

    public CommandResult demo() {
        DemoReq demoReq = new DemoReq();
        CommandParam<DemoReq> param = CommandParam.buildCommandParam(demoReq);
        CommandResult<Void> result = CommandResult.buildCommandResult(null);
        CommandContent<DemoReq, Void> commandContent = CommandContent.buildCommandContent(CommandEnum.DEMO, param, result);
        commandFactory.invoke(commandContent);
        return commandContent.getResult();
    }

    public CommandResult error() {
        CommandParam<Void> param = CommandParam.buildCommandParam(null);
        CommandResult<Void> result = CommandResult.buildCommandResult(null);
        CommandContent<Void, Void> commandContent = CommandContent.buildCommandContent(CommandEnum.ERROR, param, result);
        commandFactory.invoke(commandContent);
        return commandContent.getResult();
    }

}
