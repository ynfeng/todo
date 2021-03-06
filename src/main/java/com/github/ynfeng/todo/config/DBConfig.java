package com.github.ynfeng.todo.config;

public class DBConfig {
    private final String type;
    private final String url;
    private final String user;
    private final String password;
    private boolean enable;

    public DBConfig(String type, String url, String user, String password) {
        this.type = type;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String type() {
        return type;
    }

    public String url() {
        return url;
    }

    public String user() {
        return user;
    }

    public String password() {
        return password;
    }

    public void enable() {
        enable = true;
    }

    public boolean isEnable() {
        return enable;
    }

}
