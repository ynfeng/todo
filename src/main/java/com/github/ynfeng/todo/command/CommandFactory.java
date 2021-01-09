package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.TodoApplicationException;
import com.google.common.collect.Maps;
import java.util.Map;

public final class CommandFactory {
    private static final Map<String, Command> SUPPORT_COMMANDS = Maps.newHashMap();

    static {
        SUPPORT_COMMANDS.put("add", new AddItemCommand());
        SUPPORT_COMMANDS.put("list", new ListItemsCommand());
        SUPPORT_COMMANDS.put("done", new MarkItemDoneCommand());
        SUPPORT_COMMANDS.put("login", new LoginCommand());
        SUPPORT_COMMANDS.put("logout", new LogoutCommand());
        SUPPORT_COMMANDS.put("adduser", new AddUserCommand());
        SUPPORT_COMMANDS.put("export", new ExportCommand());
        SUPPORT_COMMANDS.put("import", new ImportCommand());
        SUPPORT_COMMANDS.put("dbconf", new DBConfigCommand());
        SUPPORT_COMMANDS.put("init", new InitCommand());
        SUPPORT_COMMANDS.put("whomi", new WhomiCommand());
    }

    private CommandFactory() {

    }

    public static Command createCommand(String cmd) {
        Command command = SUPPORT_COMMANDS.get(cmd);
        if (command == null) {
            throw new TodoApplicationException("unsupported command.");
        }
        return command;
    }
}
