package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.AppConfig;

public class DefaultApplicationContext implements ApplicationContext {
    private final AppConfig config;

    public DefaultApplicationContext(AppConfig config) {
        this.config = config;
    }

    @Override
    public AppConfig appConfig() {
        return config;
    }
}
