package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.print;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.config.DBConfig;
import java.util.Optional;

public class InitCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        Optional<DBConfig> dbConfigOptional = context.dbConfig();
        if (!dbConfigOptional.isPresent()) {
            print("no database configured!");
        } else {
            context.enableDatabase();
            print("database is initialized!");
        }
    }
}
