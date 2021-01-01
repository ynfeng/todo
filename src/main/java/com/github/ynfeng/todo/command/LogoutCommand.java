package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.user.CurrentUser;

public class LogoutCommand implements Command {
    @Override
    public void execute(Args args) {
        CurrentUser.remove();
        println("Logout success!");
    }
}
