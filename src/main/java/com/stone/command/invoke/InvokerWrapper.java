package com.stone.command.invoke;

import lombok.Data;

@Data
public class InvokerWrapper {

    private Invoker invoker;

    private Class reqClass;

    private Class resClass;

    public InvokerWrapper(Invoker invoker) {
        this.invoker = invoker;
    }

    public InvokerWrapper(Invoker invoker, Class reqClass, Class resClass) {
        this.invoker = invoker;
        this.reqClass = reqClass;
        this.resClass = resClass;
    }

    public boolean checkType(Object req, Object res) {
        return checkType(req, reqClass) && checkType(res, resClass);
    }

    private boolean checkType(Object obj, Class tClass) {
        if (tClass == null) {
            return true;
        } else if (obj == null && tClass == Void.class) {
            return true;
        } else if (obj != null && obj.getClass().isAssignableFrom(tClass)) {
            return true;
        } else {
            return false;
        }
    }

}
