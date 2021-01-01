package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.item.FileBasedItemRepository;
import com.github.ynfeng.todo.item.ItemRepository;

public class ApplicationContext {
    private final ItemRepository itemRepository;
    private static ApplicationContext INSTANCE;

    public ApplicationContext(AppConfig config) {
        String baseDir = System.getProperty("user.home");
        itemRepository = new FileBasedItemRepository(config.getConfigOrDefault("dataDir", baseDir + "/.todo/"));
        INSTANCE = this;
    }

    public static ItemRepository itemRepository() {
        return INSTANCE.itemRepository;
    }
}
