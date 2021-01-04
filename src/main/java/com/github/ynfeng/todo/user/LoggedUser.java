package com.github.ynfeng.todo.user;

public class LoggedUser {
    private final String username;

    public LoggedUser(String username) {
        this.username = username;
    }

    public String username() {
        return username;
    }
}
