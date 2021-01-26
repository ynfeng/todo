package com.github.ynfeng.todo.user;

public class LogoutService {
    public void logout() {
        CurrentUser.remove();
    }
}
