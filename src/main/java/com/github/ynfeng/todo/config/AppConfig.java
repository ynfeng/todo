package com.github.ynfeng.todo.config;

public interface AppConfig {
    default String defaultDataDir() {
        return System.getProperty("user.home") + "/.todo/";
    }
}
