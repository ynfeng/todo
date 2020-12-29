package com.github.ynfeng.todo;

public class DefaultFileBasedStorageConfig implements AppConfig {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getConfig(String key) {
        // hard code.
        return (T) "/tmp/todo/app/";
    }
}
