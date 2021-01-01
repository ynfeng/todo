package com.github.ynfeng.todo;

import com.github.ynfeng.todo.command.CommandFactory;

public class TodoApp {
    private final ApplicationContext context;

    public TodoApp(ApplicationContext context) {
        this.context = context;
    }

    public void run(Args args) {
        String cmd = args.cmd();
        CommandFactory.createCommand(cmd).execute(context, args);
    }
}
