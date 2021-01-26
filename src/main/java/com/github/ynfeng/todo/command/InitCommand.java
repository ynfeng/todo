package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printDBConfig;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.config.DBConfig;
import java.util.Optional;

public class InitCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        Optional<DBConfig> dbConfigOptional = context.dbConfig();
        if (!dbConfigOptional.isPresent()) {
            printDBConfig("no database configured!");
            return;
        }
        context.enableDatabase();
        printDBConfig("database is initialized!");
    }
}
