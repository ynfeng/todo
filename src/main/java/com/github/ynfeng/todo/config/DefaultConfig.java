package com.github.ynfeng.todo.config;

public class DefaultConfig implements AppConfig {
    @Override
    public <T> T getConfigOrDefault(String key, T defaultValue) {
        return defaultValue;
    }
}