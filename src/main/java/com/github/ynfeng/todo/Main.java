package com.github.ynfeng.todo;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.config.DefaultConfig;

public final class Main {

    public static void main(String[] args) {
        TodoApp app = new TodoApp(new DefaultConfig());
        try {
            app.run(Args.of(args));
        } catch (Exception exception) {
            println(exception.getMessage());
        }
    }
}
