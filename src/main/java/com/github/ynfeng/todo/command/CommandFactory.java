package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.TodoApplicationException;
import com.google.common.collect.Maps;
import java.util.Map;

public final class CommandFactory {
    private static final Map<String, Command> SUPPORTED_COMMANDS = Maps.newHashMap();

    static {
        SUPPORTED_COMMANDS.put("add", new AddItemCommand());
        SUPPORTED_COMMANDS.put("list", new ListItemsCommand());
        SUPPORTED_COMMANDS.put("done", new MarkItemDoneCommand());
        SUPPORTED_COMMANDS.put("login", new LoginCommand());
        SUPPORTED_COMMANDS.put("logout", new LogoutCommand());
        SUPPORTED_COMMANDS.put("adduser", new AddUserCommand());
        SUPPORTED_COMMANDS.put("export", new ExportCommand());
        SUPPORTED_COMMANDS.put("import", new ImportCommand());
        SUPPORTED_COMMANDS.put("dbconf", new DBConfigCommand());
        SUPPORTED_COMMANDS.put("init", new InitCommand());
        SUPPORTED_COMMANDS.put("whomi", new WhomiCommand());
    }

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
