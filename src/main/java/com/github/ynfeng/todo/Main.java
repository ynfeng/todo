package com.github.ynfeng.todo;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.config.DefaultConfig;

public final class Main {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new DefaultApplicationContext(new DefaultConfig());
            new TodoApp(context).run(Args.of(args));
        } catch (TodoApplicationException exception) {
            println(exception.getMessage());
        }
    }
}
