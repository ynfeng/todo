package com.github.ynfeng.todo;

import java.io.PrintStream;

public class TodoApp {
    private final Console console;
    public final ItemRepository itemRepository;

    public TodoApp(AppConfig config, PrintStream out) {
        console = new Console(out);
        String baseDir = System.getProperty("user.home");
        itemRepository = new FileBasedItemRepository(config.getConfigOrDefault("dataDir", baseDir + "/.todo/"));
    }

    public void run(String... args) {
        String cmd = args[0];
        CommandFactory.createCommand(cmd).execute(args, console, itemRepository);
    }
}
