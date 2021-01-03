package com.github.ynfeng.todo.config;

public interface AppConfig {
    <T> T getConfigOrDefault(String key, T defaultValue);

    default String defaultDataDir() {
        return System.getProperty("user.home") + "/.todo/";
    }
}
