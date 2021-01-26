package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.printSummary;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.CurrentUser;

public class ListItemsCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoList todoList = context.todoList(CurrentUser.username());
        if (args.has("--all")) {
            printItems(todoList.all());
            printSummary(todoList.all());
            return;
        }
        printItems(todoList.unFinishedItems());
    }
}
