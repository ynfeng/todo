package com.github.ynfeng.todo.todolist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryBasedTodoList implements TodoList {
    public final List<Item> items = new ArrayList<>();

    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public Optional<Item> get(int index) {
        if (index < 0 || index >= items.size()) {
            return Optional.empty();
        }
        return Optional.of(items.get(index));
    }

    @Override
    public List<Item> unFinishedItems() {
        return items.stream().filter(item -> !item.isDone()).collect(Collectors.toList());
    }

    @Override
    public List<Item> all() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public void update(Item item) {

    }

}
