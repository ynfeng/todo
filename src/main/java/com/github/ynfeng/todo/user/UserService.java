package com.github.ynfeng.todo.user;

import com.github.ynfeng.todo.ApplicationContext;

public class UserService {
    private final FileBasedUserRepository userRepository;

    public UserService(ApplicationContext context) {
        userRepository = new FileBasedUserRepository(context.appConfig().defaultDataDir());
    }

    public void addUser(String username, String password) throws UserAlreadyExistsException {
        if (userExists(username)) {
            throw new UserAlreadyExistsException();
        }
        User user = new User(username, password);
        userRepository.add(user);
    }

    private boolean userExists(String username) {
        return userRepository.findUser(username).isPresent();
    }
}
