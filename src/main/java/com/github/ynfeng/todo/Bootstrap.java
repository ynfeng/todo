package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;
import com.github.ynfeng.todo.todolist.FileBasedTodoList;

public class Bootstrap {

    public void boot(AppConfig config) {
        ApplicationContext.setTodoList(newTodoList(config));
    }

    private static FileBasedTodoList newTodoList(AppConfig config) {
        String baseDir = System.getProperty("user.home");
        return new FileBasedTodoList(config.getConfigOrDefault("dataDir", baseDir + "/.todo/"));
    }
}
