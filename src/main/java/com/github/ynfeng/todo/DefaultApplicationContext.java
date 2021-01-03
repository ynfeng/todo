package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.todolist.FileBasedTodoList;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.FileBasedUserRepository;
import com.github.ynfeng.todo.user.UserRepository;

public class DefaultApplicationContext implements ApplicationContext {
    private final AppConfig config;

    public DefaultApplicationContext(AppConfig config) {
        this.config = config;
    }

    @Override
    public TodoList todoList(String userName) {
        return new FileBasedTodoList(config.getConfigOrDefault("dataDir", config.defaultDataDir()));
    }

    @Override
    public UserRepository userRepository() {
        return new FileBasedUserRepository(config.getConfigOrDefault("dataDir", config.defaultDataDir()));
    }
}
