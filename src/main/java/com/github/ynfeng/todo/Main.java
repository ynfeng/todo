package com.github.ynfeng.todo;

public final class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Invalid parameters.");
            return;
        }
        TodoApp app = new TodoApp(new DefaultFileBasedStorageConfig(), System.out);
        app.run(args);
    }
}
