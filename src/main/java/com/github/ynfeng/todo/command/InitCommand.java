package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Console;
import com.github.ynfeng.todo.todolist.NoDatabaseConfiguredException;
import com.github.ynfeng.todo.todolist.TodoListService;

public class InitCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoListService todoListService = new TodoListService(context);
        try {
            todoListService.enableDatabase();
        } catch (NoDatabaseConfiguredException e) {
            Console.print("no database configured!");
            return;
        }
        Console.print("database is initialized!");
    }
}
