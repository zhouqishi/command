package com.stone.command.controller;

import com.stone.command.dto.CommandResult;
import com.stone.command.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("demo")
    public CommandResult demo() {
        return demoService.demo();
    }

    @RequestMapping("error")
    public CommandResult error() {
        return demoService.error();
    }

}
