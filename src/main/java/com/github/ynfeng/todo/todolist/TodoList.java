package com.github.ynfeng.todo.todolist;

import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.storage.Storage;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoList {
    private final List<Item> items;
    private final Storage<Item> storage;

    public TodoList(Storage<Item> storage) {
        this.storage = storage;
        items = storage.loadAll();
    }

    public void add(Item item) {
        items.add(item);
        storage.append(item);
    }

    public Optional<Item> get(int index) {
        if (index < 0 || index >= items.size()) {
            return Optional.empty();
        }
        return Optional.of(items.get(index));
    }

    public List<Item> unFinishedItems() {
        return items.stream().filter(item -> !item.isDone()).collect(Collectors.toList());
    }

    public List<Item> all() {
        return Collections.unmodifiableList(items);
    }

    public void update(Item item) {
        storage.updateAll(items);
    }

    public void itemDone(int itemIdx) {
        Item item = get(itemIdx - 1).orElseThrow(
            () -> new TodoApplicationException("No such item"));
        item.done();
        update(item);
    }

    public void addAll(List<Item> itemList) {
        itemList.forEach(this::add);
    }
}
