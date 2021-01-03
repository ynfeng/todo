package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;
import static com.github.ynfeng.todo.Console.readPassword;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.user.User;

public class AddUserCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        String username = args.getByIndex(2, "Usage: adduser -u <username>");
        if (userExists(context, username)) {
            println("user already exists.");
            return;
        }
        addUser(context, username);
        println("user added.");
    }

    private void addUser(ApplicationContext context, String username) {
        println("Password:");
        String password = readPassword();
        User user = new User(username, password);
        context.userRepository().add(user);
    }

    private boolean userExists(ApplicationContext context, String username) {
        return context.userRepository().findUser(username).isPresent();
    }
}
