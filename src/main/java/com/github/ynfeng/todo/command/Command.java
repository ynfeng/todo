package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;

@FunctionalInterface
public interface Command {
    void execute(ApplicationContext context, Args args);
}
