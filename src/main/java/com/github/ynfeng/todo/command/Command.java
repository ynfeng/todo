package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.Args;

@FunctionalInterface
public interface Command {
    void execute(Args args);
}
