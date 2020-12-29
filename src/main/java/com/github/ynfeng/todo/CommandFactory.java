package com.github.ynfeng.todo;

public class CommandFactory {
    private CommandFactory() {

    }

    public static Command createCommand(String cmd) {
        switch (cmd) {
            case "add":
                return new AddItemCommand();
            case "list":
                return new ListFinishedItemsCommand();
            case "done":
                return new MarkItemDoneCommand();
            default:
                throw new IllegalArgumentException("unsupported command.");
        }
    }
}
