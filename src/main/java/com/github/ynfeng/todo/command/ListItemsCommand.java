package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.printSummary;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.TodoList;

public class ListItemsCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoList todoList = context.todoList(null);
        if (args.has("--all")) {
            printItems(todoList.all());
            printSummary(todoList.all());
        } else {
            printItems(todoList.unFinishedItems());
        }
    }
}
