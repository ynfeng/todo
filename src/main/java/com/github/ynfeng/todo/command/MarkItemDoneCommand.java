package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.CurrentUser;

public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoList todoList = context.todoList(CurrentUser.username());
        int itemIdx = Integer.parseInt(args.getByIndexOrThrowException(1, "Usage: done <item index>"));
        todoList.itemDone(itemIdx);
        println("Item %d done", itemIdx);
    }

}
