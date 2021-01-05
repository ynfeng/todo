package com.github.ynfeng.todo.config;

import com.github.ynfeng.todo.Console;

public class DBConfig {
    private final String type;
    private final String url;
    private final String user;
    private final String password;

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

    public void print() {
        Console.println("type: %s", type());
        Console.println("url: %s", url());
        Console.println("user: %s", user());
        Console.println("password: %s", password());
    }
}
