package com.github.ynfeng.todo.todolist;

import java.util.List;
import java.util.Optional;

public interface TodoList {
    void add(Item item);

    Optional<Item> get(int index);

    List<Item> unFinishedItems();

    List<Item> all();

    void update(Item item);
}
