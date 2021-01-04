package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.persistence.FileStorage;
import com.github.ynfeng.todo.todolist.Item;
import com.github.ynfeng.todo.user.CurrentUser;
import java.util.List;

public class ImportCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        String importPath = args.getByIndex(2, "Usage: import -f <file path>");
        FileStorage<Item> importFile = new FileStorage<>(importPath, Item.class);
        List<Item> itemList = importFile.loadAll();
        context.todoList(CurrentUser.username()).addAll(itemList);
    }
}
