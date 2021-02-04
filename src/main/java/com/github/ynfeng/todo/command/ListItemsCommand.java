package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.printSummary;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.Item;
import com.github.ynfeng.todo.todolist.TodoListService;
import java.util.List;

public class ListItemsCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoListService todoListService = new TodoListService(context);
        if (args.has("--all")) {
            printAll(todoListService.listAll());
            return;
        }
        printItems(todoListService.unFinishedItems());
    }

    private static void printAll(List<Item> all) {
        printItems(all);
        printSummary(all);
    }
}
