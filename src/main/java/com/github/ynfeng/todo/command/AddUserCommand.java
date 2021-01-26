package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;
import static com.github.ynfeng.todo.Console.readPassword;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.user.UserAlreadyExistsException;
import com.github.ynfeng.todo.user.UserService;

public class AddUserCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        UserService userService = new UserService(context);
        String username = args.getByIndexOrThrowException(2, "Usage: adduser -u <username>");
        try {
            String password = readInputPassword();
            userService.addUser(username, password);
        } catch (UserAlreadyExistsException e) {
            println("user already exists.");
            return;
        }
        println("user added.");
    }

    private static String readInputPassword() {
        Console.print("Password:");
        return readPassword();
    }
}
