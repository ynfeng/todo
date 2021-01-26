package com.github.ynfeng.todo.todolist;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.storage.FileStorage;
import com.github.ynfeng.todo.storage.ItemDB;
import com.github.ynfeng.todo.storage.Storage;
import com.github.ynfeng.todo.user.CurrentUser;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TodoListService {
    private final ApplicationContext context;
    private final FileStorage<DBConfig> dbConfigStorage;

    public TodoListService(ApplicationContext context) {
        this.context = context;
        dbConfigStorage = new FileStorage<>(context.appConfig().defaultDataDir() + "/.dbconf.json", DBConfig.class);
    }

    public TodoList addItem(String itemName) {
        TodoList todoList = getTodoList();
        Item item = Item.newItem(itemName);
        todoList.add(item);
        return todoList;
    }

    public void itemDone(int itemIdx) {
        TodoList todoList = getTodoList();
        todoList.itemDone(itemIdx);
    }

    public TodoList getTodoList() {
        Storage<Item> storage = createStorage();
        return new TodoList(storage);
    }

    public void exportTo(String exportFilePath) {
        List<Item> todoList = getTodoList().all();
        FileStorage<Item> exportFile = new FileStorage<>(exportFilePath, Item.class);
        exportFile.appendAll(todoList);
    }

    public void importFrom(String importFilePath) {
        FileStorage<Item> importFile = new FileStorage<>(importFilePath, Item.class);
        getTodoList().addAll(importFile.loadAll());
    }

    public void enableDatabase() throws NoDatabaseConfiguredException {
        Optional<DBConfig> dbConfigOptional = getDBConfig();
        if (!dbConfigOptional.isPresent()) {
            throw new NoDatabaseConfiguredException();
        }
        DBConfig dbConfig = dbConfigOptional.get();
        dbConfig.enable();
        saveDBConfig(dbConfig);
        exportFromFileToDatabase();
    }

    private void saveDBConfig(DBConfig dbConfig) {
        dbConfigStorage.updateAll(Collections.singletonList(dbConfig));
    }

    private void exportFromFileToDatabase() {
        Storage<Item> fileStorage = createFileStorage();
        Storage<Item> dbStorage = createDBStorage(getDBConfig().get());
        dbStorage.appendAll(fileStorage.loadAll());
    }

    private Storage<Item> createStorage() {
        Optional<DBConfig> dbConfig = getDBConfig();
        if (dbConfig.isPresent() && dbConfig.get().isEnable()) {
            return createDBStorage(dbConfig.get());
        }
        return createFileStorage();
    }

    private ItemDB createDBStorage(DBConfig dbConfig) {
        return ItemDB.create(CurrentUser.username(), dbConfig);
    }

    private Storage<Item> createFileStorage() {
        AppConfig appConfig = context.appConfig();
        String dataDir = appConfig.defaultDataDir() + '/' + CurrentUser.username();
        return new FileStorage<>(dataDir + "/data.json", Item.class);
    }

    public void configDB(DBConfig dbConfig) {
        dbConfigStorage.updateAll(Collections.singletonList(dbConfig));
    }

    public Optional<DBConfig> getDBConfig() {
        List<DBConfig> configList = dbConfigStorage.loadAll();
        if (configList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(configList.get(0));
    }
}
