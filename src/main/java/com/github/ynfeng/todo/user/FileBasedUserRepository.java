package com.github.ynfeng.todo.user;

import com.github.ynfeng.todo.storage.FileStorage;
import java.util.Optional;

public class FileBasedUserRepository implements UserRepository {
    private final FileStorage<User> storage;

    public FileBasedUserRepository(String dataDir) {
        storage = new FileStorage<>(dataDir + "/.user-config.json", User.class);
    }

    @Override
    public Optional<User> findUser(String name) {
        return storage.loadAll().stream()
            .filter(user -> user.name().equals(name))
            .findFirst();
    }

    @Override
    public void add(User user) {
        storage.append(user);
    }
}
