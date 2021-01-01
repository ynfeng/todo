package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.user.CurrentUser;

public class LogoutCommand implements Command {
    @Override
    public void execute(Args args) {
        CurrentUser.remove();
    }
}
