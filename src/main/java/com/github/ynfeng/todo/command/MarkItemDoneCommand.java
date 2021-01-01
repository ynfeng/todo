package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.todolist.Item;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.CurrentUser;

public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(ApplicationContext context, Args args) {
        TodoList todoList = context.todoList(CurrentUser.username());
        int itemIdx = Integer.parseInt(args.getByIndex(1, "Usage: done <item index>"));
        Item item = todoList.get(itemIdx - 1).orElseThrow(
            () -> new TodoApplicationException("No such item"));
        item.done();
        todoList.update(item);
        println("Item %d done", itemIdx);
    }
}
