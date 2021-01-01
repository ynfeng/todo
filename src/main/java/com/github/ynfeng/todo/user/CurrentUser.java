package com.github.ynfeng.todo.user;

public class CurrentUser {
    private User user;
    private final static CurrentUser INSTANCE = new CurrentUser();

    private CurrentUser() {

    }

    public static void set(User user) {
        INSTANCE.user = user;
    }

    public static User get() {
        return INSTANCE.user;
    }

    public static void remove() {
        INSTANCE.user = null;
    }
}
