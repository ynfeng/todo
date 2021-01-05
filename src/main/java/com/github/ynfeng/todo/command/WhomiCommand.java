package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.user.CurrentUser;

public class WhomiCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        println(CurrentUser.username());
    }
}
