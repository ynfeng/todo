package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.DefaultConfig;

public final class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Invalid parameters.");
            return;
        }
        TodoApp app = new TodoApp(new DefaultConfig());
        app.run(null, args);
    }
}
