package com.github.ynfeng.todo.todolist;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.user.CurrentUser;

public class TodoListService {
    private final ApplicationContext context;

    public TodoListService(ApplicationContext context) {
        this.context = context;
    }

    public TodoList addItem(String itemName) {
        TodoList todoList = context.todoList(CurrentUser.username());
        Item item = Item.newItem(itemName);
        todoList.add(item);
        return todoList;
    }

    public void itemDone(int itemIdx) {
        TodoList todoList = context.todoList(CurrentUser.username());
        todoList.itemDone(itemIdx);
    }
}
