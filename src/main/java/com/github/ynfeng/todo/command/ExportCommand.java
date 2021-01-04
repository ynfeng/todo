package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.persistence.FileStorage;
import com.github.ynfeng.todo.todolist.Item;
import com.github.ynfeng.todo.user.CurrentUser;
import java.util.List;

public class ExportCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        String exportPath = args.getByIndex(2, "Usage: export > <file path>");
        List<Item> todoList = context.todoList(CurrentUser.username()).all();
        FileStorage<Item> exportFile = new FileStorage<>(exportPath, Item.class);
        exportFile.appendAll(todoList);
    }
}
