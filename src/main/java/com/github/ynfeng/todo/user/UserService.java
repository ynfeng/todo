package com.github.ynfeng.todo.user;

import com.github.ynfeng.todo.ApplicationContext;

public class UserService {
    private final ApplicationContext context;

    public UserService(ApplicationContext context) {
        this.context = context;
    }

    public void addUser(String username, String password) throws UserAlreadyExistsException {
        if (userExists(username)) {
            throw new UserAlreadyExistsException();
        }
        User user = new User(username, password);
        context.userRepository().add(user);
    }

    private boolean userExists(String username) {
        return context.userRepository().findUser(username).isPresent();
    }
}
