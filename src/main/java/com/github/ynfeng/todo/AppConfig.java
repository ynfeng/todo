package com.github.ynfeng.todo;

public interface AppConfig {
    <T> T getConfigOrDefault(String key, T defaultValue);
}
