package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.TodoApplicationException;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public final class CommandFactory {
    private static final Map<String, Command> SUPPORTED_COMMANDS = ImmutableMap.<String, Command>builder()
        .put("add", new AddItemCommand())
        .put("list", new ListItemsCommand())
        .put("done", new MarkItemDoneCommand())
        .put("login", new LoginCommand())
        .put("logout", new LogoutCommand())
        .put("adduser", new AddUserCommand())
        .put("export", new ExportCommand())
        .put("import", new ImportCommand())
        .put("dbconf", new DBConfigCommand())
        .put("init", new InitCommand())
        .put("whomi", new WhomiCommand())
        .build();

    private CommandFactory() {

    }

    public static Command getCommand(String cmd) {
        Command command = SUPPORTED_COMMANDS.get(cmd);
        if (command == null) {
            throw new TodoApplicationException("unsupported command.");
        }
        return command;
    }
}
