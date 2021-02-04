package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.TodoListService;

public class AddItemCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoListService todoListService = new TodoListService(context);
        String itemName = args.getByIndexOrThrowException(1, "Usage: add <item name>");
        todoListService.addItem(itemName);
        printItems(todoListService.unFinishedItems());
        println("Item %s added", itemName);
    }
}
