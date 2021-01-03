package com.github.ynfeng.todo.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUser(String name);

    void add(User user);
}
