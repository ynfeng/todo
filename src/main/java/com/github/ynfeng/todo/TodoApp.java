package com.github.ynfeng.todo;

import java.io.PrintStream;

public class TodoApp {
    private final Console console;
    public final ItemRepository itemRepository;

    public TodoApp(AppConfig config, PrintStream out) {
        console = new Console(out);
        itemRepository = new FileBasedItemRepository(config.getConfigOrDefault("dataDir", "~/.todo/data/"));
    }

    public void run(String... args) {
        String cmd = args[0];
        CommandFactory.createCommand(cmd).execute(args, console, itemRepository);
    }
}
