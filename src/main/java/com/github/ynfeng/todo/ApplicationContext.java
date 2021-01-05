package com.github.ynfeng.todo;

import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.UserRepository;
import java.util.Optional;

public interface ApplicationContext {
    TodoList todoList(String userName);

    UserRepository userRepository();

    void dbConfig(DBConfig dbConfig);

    Optional<DBConfig> dbConfig();
}
