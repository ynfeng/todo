package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.print;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.db.DBConfig;
import java.util.Optional;

public class DBConfigCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        if (args.has("-s")) {
            printDBConfig(context);
        } else {
            setDBConfig(context, args);
        }
    }

    private static void printDBConfig(ApplicationContext context) {
        Optional<DBConfig> configOptional = context.dbConfig();
        if (!configOptional.isPresent()) {
            print("db config has not set!");
        } else {
            configOptional.get().print();
        }
    }

    private static void setDBConfig(ApplicationContext context, Args args) {
        String usage = "Usage: -t <db type> -l <jdbc url> -u <db user> -p <db user password>";
        String type = args.getByIndex(2, usage);
        String url = args.getByIndex(4, usage);
        String user = args.getByIndex(6, usage);
        String password = args.getByIndex(8, usage);
        DBConfig dbConfig = new DBConfig(type, url, user, password);
        context.dbConfig(dbConfig);
        print("db config has been set!");
    }
}
