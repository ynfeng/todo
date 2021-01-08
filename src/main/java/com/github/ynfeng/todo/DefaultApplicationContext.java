package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.storage.FileStorage;
import com.github.ynfeng.todo.storage.ItemDB;
import com.github.ynfeng.todo.storage.Storage;
import com.github.ynfeng.todo.todolist.DefaultTodoList;
import com.github.ynfeng.todo.todolist.Item;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.CurrentUser;
import com.github.ynfeng.todo.user.FileBasedUserRepository;
import com.github.ynfeng.todo.user.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DefaultApplicationContext implements ApplicationContext {
    private final AppConfig config;
    private final FileStorage<DBConfig> dbConfigStorage;

    public DefaultApplicationContext(AppConfig config) {
        this.config = config;
        dbConfigStorage = new FileStorage<>(config.defaultDataDir() + "/.dbconf.json", DBConfig.class);
    }

    @Override
    public TodoList todoList(String userName) {
        Storage<Item> storage = createStorage();
        return new DefaultTodoList(storage);
    }

    private Storage<Item> createStorage() {
        if (dbConfig().isPresent() && dbConfig().get().isEnable()) {
            return createDBStorage();
        } else {
            return createFileStorage();
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private ItemDB createDBStorage() {
        return ItemDB.create(CurrentUser.username(), dbConfig().get());
    }

    private Storage<Item> createFileStorage() {
        String dataDir = config.getConfigOrDefault("dataDir", config.defaultDataDir()) + '/' + CurrentUser.username();
        return new FileStorage<>(dataDir + "/data.json", Item.class);
    }

    @Override
    public UserRepository userRepository() {
        return new FileBasedUserRepository(config.getConfigOrDefault("dataDir", config.defaultDataDir()));
    }

    @Override
    public void dbConfig(DBConfig dbConfig) {
        dbConfigStorage.updateAll(Collections.singletonList(dbConfig));
    }

    @Override
    public Optional<DBConfig> dbConfig() {
        List<DBConfig> configList = dbConfigStorage.loadAll();
        if (configList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(configList.get(0));
    }

    @Override
    public void enableDatabase() {
        dbConfig().ifPresent(conf -> {
            conf.enable();
            exportFromFileToDatabase();
            dbConfigStorage.updateAll(Collections.singletonList(conf));
        });
    }

    private void exportFromFileToDatabase() {
        Storage<Item> fileStorage = createFileStorage();
        Storage<Item> dbStorage = createDBStorage();
        dbStorage.appendAll(fileStorage.loadAll());
    }
}
