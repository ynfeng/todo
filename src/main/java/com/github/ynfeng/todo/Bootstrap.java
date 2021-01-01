package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.item.FileBasedItemRepository;

public class Bootstrap {

    public void boot(AppConfig config) {
        FileBasedItemRepository itemRepository = newItemRepository(config);
        ApplicationContext.setItemRepository(itemRepository);
    }

    private static FileBasedItemRepository newItemRepository(AppConfig config) {
        String baseDir = System.getProperty("user.home");
        return new FileBasedItemRepository(config.getConfigOrDefault("dataDir", baseDir + "/.todo/"));
    }
}
