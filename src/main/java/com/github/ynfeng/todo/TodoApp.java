package com.github.ynfeng.todo;

import com.github.ynfeng.todo.command.CommandFactory;
import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.persistence.FileBasedItemRepository;
import com.github.ynfeng.todo.persistence.ItemRepository;

public class TodoApp {
    public final ItemRepository itemRepository;

    public TodoApp(AppConfig config) {
        String baseDir = System.getProperty("user.home");
        itemRepository = new FileBasedItemRepository(config.getConfigOrDefault("dataDir", baseDir + "/.todo/"));
    }

    public void run(String... args) {
        String cmd = args[0];
        CommandFactory.createCommand(cmd).execute(args, itemRepository);
    }
}
