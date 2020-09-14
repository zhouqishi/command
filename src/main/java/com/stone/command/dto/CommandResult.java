package com.stone.command.dto;

import lombok.Data;

@Data
public class CommandResult<R> {

    public enum ResultEnums {
        success(0),
        failure(1),
        ;

        private Integer code;

        public Integer getCode() {
            return code;
        }

        ResultEnums(Integer code) {
            this.code = code;
        }
    }

    private Integer code;

    private String msg;

    private R body;

    public static <R> CommandResult<R> buildCommandResult(R body) {
        CommandResult<R> result = new CommandResult<>();
        result.setCode(ResultEnums.success.getCode());
        result.setBody(body);
        return result;
    }

    public void failure(String msg) {
        this.code = ResultEnums.failure.getCode();
        this.msg = msg;
        this.body = null;
    }

    public void failure(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.body = null;
    }

}
