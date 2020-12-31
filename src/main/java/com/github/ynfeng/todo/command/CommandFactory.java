package com.github.ynfeng.todo.command;

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
            default:
                throw new IllegalArgumentException("unsupported command.");
        }
    }
}
