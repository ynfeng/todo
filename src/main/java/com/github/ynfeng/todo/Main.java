package com.github.ynfeng.todo;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.config.DefaultConfig;
import com.github.ynfeng.todo.item.FileBasedItemRepository;

public final class Main {

    public static void main(String[] args) {
        DefaultConfig config = new DefaultConfig();
        FileBasedItemRepository itemRepository = newItemRepository(config);
        ApplicationContext.setItemRepository(itemRepository);
        TodoApp app = new TodoApp();
        try {
            app.run(Args.of(args));
        } catch (TodoApplicationException exception) {
            println(exception.getMessage());
        }
    }

    private static FileBasedItemRepository newItemRepository(DefaultConfig config) {
        String baseDir = System.getProperty("user.home");
        return new FileBasedItemRepository(config.getConfigOrDefault("dataDir", baseDir + "/.todo/"));
    }
}
