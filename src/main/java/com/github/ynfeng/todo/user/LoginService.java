package com.github.ynfeng.todo.user;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.TodoApplicationException;

public class LoginService {
    private final FileBasedUserRepository userRepository;

    public LoginService(ApplicationContext context) {
        userRepository = new FileBasedUserRepository(context.appConfig().defaultDataDir());
    }

    public boolean login(String username, String password) {
        User user = findUserOrThrowException(username);
        if (user.password().equals(password)) {
            CurrentUser.set(new LoggedUser(user.name()));
            return true;
        }
        CurrentUser.remove();
        return false;
    }

    private User findUserOrThrowException(String username) {
        return userRepository.findUser(username)
            .orElseThrow(() -> new TodoApplicationException("No such user!"));
    }
}
