package com.github.ynfeng.todo;

import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.UserRepository;

public class ApplicationContext {
    private final static ApplicationContext INSTANCE = new ApplicationContext();
    private TodoList todoList;
    private UserRepository userRepository;

    private ApplicationContext() {

    }

    public static TodoList todoList() {
        return INSTANCE.todoList;
    }

    public static UserRepository userRepository() {
        return INSTANCE.userRepository;
    }

    public static void setUserRepository(UserRepository userRepository) {
        INSTANCE.userRepository = userRepository;
    }

    public static void setTodoList(TodoList todoList) {
        INSTANCE.todoList = todoList;
    }
}
