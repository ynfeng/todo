package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.storage.FileStorage;
import com.github.ynfeng.todo.todolist.Item;
import com.github.ynfeng.todo.todolist.TodoListService;
import com.github.ynfeng.todo.user.CurrentUser;
import java.util.List;

public class ExportCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoListService todoListService = new TodoListService(context);
        String exportPath = args.getByIndexOrThrowException(2, "Usage: export -o <file path>");
        todoListService.exportTo(exportPath);
    }
}
