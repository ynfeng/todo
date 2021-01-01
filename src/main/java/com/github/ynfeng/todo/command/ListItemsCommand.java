package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.printSummary;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.TodoList;

public class ListItemsCommand implements Command {
    @Override
    public void execute(Args args) {
        TodoList todoList = ApplicationContext.todoList();
        if (args.has("--all")) {
            printItems(todoList.all());
            printSummary(todoList.all());
        } else {
            printItems(todoList.unFinishedItems());
        }
    }
}
