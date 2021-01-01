package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.todolist.FileBasedTodoList;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.UserRepository;

public class DefaultApplicationContext implements ApplicationContext {
    private AppConfig config;

    public DefaultApplicationContext(AppConfig config) {
        this.config = config;
    }

    @Override
    public TodoList todoList(String userName) {
        String home = System.getProperty("user.home");
        return new FileBasedTodoList(config.getConfigOrDefault("dataDir", home + "/.todo/"));
    }

    @Override
    public UserRepository userRepository() {
        return null;
    }
}
