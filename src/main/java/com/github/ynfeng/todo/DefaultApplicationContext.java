package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.persistence.FileStorage;
import com.github.ynfeng.todo.todolist.FileBasedTodoList;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.FileBasedUserRepository;
import com.github.ynfeng.todo.user.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DefaultApplicationContext implements ApplicationContext {
    private final AppConfig config;
    private final FileStorage<DBConfig> dbConfigStorate;

    public DefaultApplicationContext(AppConfig config) {
        this.config = config;
        dbConfigStorate = new FileStorage<>(config.defaultDataDir() + "/.dbconf.json", DBConfig.class);
    }

    @Override
    public TodoList todoList(String userName) {
        return new FileBasedTodoList(config.getConfigOrDefault("dataDir", config.defaultDataDir()));
    }

    @Override
    public UserRepository userRepository() {
        return new FileBasedUserRepository(config.getConfigOrDefault("dataDir", config.defaultDataDir()));
    }

    @Override
    public void dbConfig(DBConfig dbConfig) {
        dbConfigStorate.updateAll(Collections.singletonList(dbConfig));
    }

    @Override
    public Optional<DBConfig> dbConfig() {
        List<DBConfig> configList = dbConfigStorate.loadAll();
        if (configList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(configList.get(0));
    }
}
