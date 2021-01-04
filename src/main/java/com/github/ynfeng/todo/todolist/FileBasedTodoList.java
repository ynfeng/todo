package com.github.ynfeng.todo.todolist;

import com.github.ynfeng.todo.persistence.FileStorage;
import com.github.ynfeng.todo.user.CurrentUser;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileBasedTodoList implements TodoList {
    private final List<Item> items;
    private final FileStorage<Item> storage;

    public FileBasedTodoList(String dataDir) {
        storage = new FileStorage<>(dataDir + '/' + CurrentUser.username() + "/data.json", Item.class);
        items = storage.loadAll();
    }

    @Override
    public void add(Item item) {
        items.add(item);
        storage.append(item);
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
        storage.updateAll(items);
    }

    @Override
    public void addAll(List<Item> itemList) {
        itemList.forEach(this::add);
    }
}
