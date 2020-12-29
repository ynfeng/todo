package com.github.ynfeng.todo;

import java.io.PrintStream;

public class TodoApp {
    private final Console console;
    public final ItemRepository itemRepository = new MemoryBasedItemRepository();

    public TodoApp(PrintStream out) {
        console = new Console(out);
    }

    public void run(String... args) {
        String cmd = args[0];
        CommandFactory.createCommand(cmd).execute(args, console, itemRepository);
    }
}
