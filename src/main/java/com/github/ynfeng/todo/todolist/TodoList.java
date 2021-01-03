package com.github.ynfeng.todo.todolist;

import com.github.ynfeng.todo.TodoApplicationException;
import java.util.List;
import java.util.Optional;

public interface TodoList {
    void add(Item item);

    Optional<Item> get(int index);

    List<Item> unFinishedItems();

    List<Item> all();

    void update(Item item);

    default void itemDone(int itemIdx) {
        Item item = get(itemIdx - 1).orElseThrow(
            () -> new TodoApplicationException("No such item"));
        item.done();
        update(item);
    }
}
