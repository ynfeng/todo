package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.config.DBConfig;
import java.util.Optional;

public class InitCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        Optional<DBConfig> dbConfigOptional = context.dbConfig();
        if (!dbConfigOptional.isPresent()) {
            Console.print("no database configured!");
            return;
        }
        context.enableDatabase();
        Console.print("database is initialized!");
    }
}
