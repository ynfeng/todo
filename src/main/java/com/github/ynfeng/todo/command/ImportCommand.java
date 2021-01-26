package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.TodoListService;

public class ImportCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoListService todoListService = new TodoListService(context);
        String importPath = args.getByIndexOrThrowException(2, "Usage: import -f <file path>");
        todoListService.importFrom(importPath);
    }
}
