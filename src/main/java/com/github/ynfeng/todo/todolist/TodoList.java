package com.github.ynfeng.todo.todolist;

import java.util.List;
import java.util.Optional;

public interface TodoList {
    void add(Item item);

    Optional<Item> getByIndex(int index);

    List<Item> listUnFinishedItem();

    List<Item> listAll();

    void update(Item item);
}
