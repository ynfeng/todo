package com.github.ynfeng.todo;

import com.github.ynfeng.todo.command.CommandFactory;

public class TodoApp {
    public TodoApp() {
    }

    public void run(Args args) {
        String cmd = args.cmd();
        CommandFactory.createCommand(cmd).execute(args);
    }
}
