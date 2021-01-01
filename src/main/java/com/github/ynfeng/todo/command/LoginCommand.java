package com.github.ynfeng.todo.command;


import static com.github.ynfeng.todo.Console.print;
import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.user.CurrentUser;
import com.github.ynfeng.todo.user.User;

public class LoginCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        checkArguments(args);
        print("Password:");
        String username = args.getByIndex(2, "Usage: login -u <username>");
        String password = Console.readPassword();
        User user = findUserOrThrowException(username, context);
        doLogin(password, user);
    }

    private void doLogin(String password, User user) {
        if (user.password().equals(password)) {
            CurrentUser.set(user);
            println("\nLogin success!");
        } else {
            CurrentUser.remove();
            println("\nLogin failed!");
        }
    }

    private User findUserOrThrowException(String username, ApplicationContext context) {
        return context.userRepository().findUser(username)
            .orElseThrow(() -> new TodoApplicationException("No such user!"));
    }

    private void checkArguments(Args args) {
        if (!args.has("-u")) {
            throw new TodoApplicationException("Usage: login -u <username>");
        }
    }
}
