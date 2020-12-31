package com.github.ynfeng.todo;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.config.DefaultConfig;

public final class Main {

    public static void main(String[] args) {
        new ApplicationContext(new DefaultConfig());
        TodoApp app = new TodoApp();
        try {
            app.run(Args.of(args));
        } catch (TodoApplicationException exception) {
            println(exception.getMessage());
        }
    }
}
