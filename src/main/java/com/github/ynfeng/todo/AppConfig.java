package com.github.ynfeng.todo;

public interface AppConfig {
    <T> T getConfig(String key);
}
