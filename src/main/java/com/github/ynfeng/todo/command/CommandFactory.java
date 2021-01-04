package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.TodoApplicationException;

public final class CommandFactory {
    private CommandFactory() {

    }

    public static Command createCommand(String cmd) {
        switch (cmd) {
            case "add":
                return new AddItemCommand();
            case "list":
                return new ListItemsCommand();
            case "done":
                return new MarkItemDoneCommand();
            case "login":
                return new LoginCommand();
            case "logout":
                return new LogoutCommand();
            case "adduser":
                return new AddUserCommand();
            case "export":
                return new ExportCommand();
            default:
                throw new TodoApplicationException("unsupported command.");
        }
    }
}
