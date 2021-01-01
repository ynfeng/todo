package com.github.ynfeng.todo;

public class TodoApplicationException extends RuntimeException {
    public TodoApplicationException(String msg) {
        super(msg);
    }

    public TodoApplicationException(Throwable t) {
        super(t);
    }
}
