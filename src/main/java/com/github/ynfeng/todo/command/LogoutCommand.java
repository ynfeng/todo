package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.user.LogoutService;

public class LogoutCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        LogoutService logoutService = new LogoutService();
        logoutService.logout();
        println("Logout success!");
    }
}
