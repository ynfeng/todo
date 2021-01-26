package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.config.DBConfig;
import java.util.Optional;

public class DBConfigCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        if (args.has("-s")) {
            printDBConfig(context);
            return;
        }
        setDBConfig(context, args);
    }

    private static void printDBConfig(ApplicationContext context) {
        Optional<DBConfig> configOptional = context.dbConfig();
        if (!configOptional.isPresent()) {
            Console.print("no database configured!");
            return;
        }
        Console.printDBConfig(configOptional.get());
    }

    private static void setDBConfig(ApplicationContext context, Args args) {
        String usage = "Usage: -t <db type> -l <jdbc url> -u <db user> -p <db user password>";
        String type = args.getByIndexOrThrowException(2, usage);
        String url = args.getByIndexOrThrowException(4, usage);
        String user = args.getByIndexOrThrowException(6, usage);
        String password = args.getByIndexOrThrowException(8, usage);
        DBConfig dbConfig = new DBConfig(type, url, user, password);
        context.dbConfig(dbConfig);
        Console.print("database is configured!");
    }
}
