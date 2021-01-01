package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.Item;
import com.github.ynfeng.todo.todolist.TodoList;

public class AddItemCommand implements Command {

    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoList todoList = context.todoList(null);
        Item item = Item.newItem(args.getByIndex(1, "Usage: add <item name>"));
        todoList.add(item);
        printItems(todoList.unFinishedItems());
        println("Item %s added", item.name());
    }
}
