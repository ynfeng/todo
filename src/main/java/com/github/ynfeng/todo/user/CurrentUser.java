package com.github.ynfeng.todo.user;

import com.github.ynfeng.todo.persistence.FileStorage;
import com.google.common.collect.Lists;

public class CurrentUser {
    private LoggedUser user;
    private static final CurrentUser INSTANCE = new CurrentUser();
    private static final FileStorage<LoggedUser> STORAGE = new FileStorage<>(System.getProperty("user.home") + "/.logged-user.json", LoggedUser.class);

    private CurrentUser() {
    }

    public static void set(LoggedUser user) {
        STORAGE.updateAll(Lists.newArrayList(user));
        INSTANCE.user = user;
    }

    public static LoggedUser get() {
        if (INSTANCE.user == null) {
            if (!STORAGE.loadAll().isEmpty()) {
                INSTANCE.user = STORAGE.loadAll().get(0);
            }
        }
        return INSTANCE.user;
    }

    public static String username() {
        if (get() == null) {
            return "anonymous";
        }
        return get().username();
    }

    public static void remove() {
        STORAGE.updateAll(Lists.newArrayList());
        INSTANCE.user = null;
    }
}
