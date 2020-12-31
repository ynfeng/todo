package com.github.ynfeng.todo.command;


import static com.github.ynfeng.todo.Console.print;
import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.persistence.ItemRepository;

public class LoginCommand implements Command {
    @Override
    public void execute(Args args, ItemRepository itemRepository) {
        print("Password:");
        String password = Console.readPassword();
        if ("12345".equals(password)) {
            println("\nLogin success!");
        } else {
            println("\nLogin falied!");
        }
    }
}
