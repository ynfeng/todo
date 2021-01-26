package com.github.ynfeng.todo.command;


import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.user.LoginService;

public class LoginCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        checkArguments(args);
        LoginService loginService = new LoginService(context);
        String username = args.getByIndexOrThrowException(2, "Usage: login -u <username>");
        String password = readPassword();
        if (loginService.login(username, password)) {
            println("\nLogin success!");
            return;
        }
        println("\nLogin failed!");
    }

    private String readPassword() {
        Console.print("Password:");
        String password = Console.readPassword();
        return password;
    }

    private static void checkArguments(Args args) {
        if (!args.has("-u")) {
            throw new TodoApplicationException("Usage: login -u <username>");
        }
    }
}
