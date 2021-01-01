package com.github.ynfeng.todo.command;


import static com.github.ynfeng.todo.Console.print;
import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.user.CurrentUser;
import com.github.ynfeng.todo.user.User;

public class LoginCommand implements Command {
    @Override
    public void execute(Args args) {
        print("Password:");
        String password = Console.readPassword();
        User user = CurrentUser.get();
        if (user.password().equals(password)) {
            println("\nLogin success!");
        } else {
            println("\nLogin failed!");
        }
    }
}
