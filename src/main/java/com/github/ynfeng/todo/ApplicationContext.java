package com.github.ynfeng.todo;

import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.UserRepository;

public interface ApplicationContext {
    TodoList todoList(String userName);

    UserRepository userRepository();
}
