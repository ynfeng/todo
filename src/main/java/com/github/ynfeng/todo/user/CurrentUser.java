package com.github.ynfeng.todo.user;

import com.github.ynfeng.todo.storage.FileStorage;
import com.google.common.collect.Lists;
import java.util.Optional;

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

    public static Optional<LoggedUser> loggedUser() {
        tryLoadUser();
        return Optional.ofNullable(INSTANCE.user);
    }

    private static void tryLoadUser() {
        if (INSTANCE.user == null && !STORAGE.loadAll().isEmpty()) {
            INSTANCE.user = STORAGE.loadAll().get(0);
        }
    }

    public static String username() {
        if (!loggedUser().isPresent()) {
            return "anonymous";
        }
        return loggedUser().get().username();
    }

    public static void remove() {
        STORAGE.updateAll(Lists.newArrayList());
        INSTANCE.user = null;
    }
}
