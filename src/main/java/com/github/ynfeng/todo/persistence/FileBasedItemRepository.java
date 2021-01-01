package com.github.ynfeng.todo.persistence;

import com.github.ynfeng.todo.Item;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileBasedItemRepository implements ItemRepository {
    private final List<Item> items;

    private final FileBasedItemStorage storage;

    public FileBasedItemRepository(String dataDir) {
        storage = new FileBasedItemStorage(dataDir);
        items = storage.loadDataFromFile();
    }

    @Override
    public void add(Item item) {
        items.add(item);
        storage.appendItem(item);
    }

    @Override
    public Optional<Item> getByIndex(int index) {
        if (index < 0 || index >= items.size()) {
            return Optional.empty();
        }
        return Optional.of(items.get(index));
    }

    @Override
    public List<Item> listUnFinishedItem() {
        return items.stream().filter(item -> !item.isDone()).collect(Collectors.toList());
    }

    @Override
    public List<Item> listAll() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public void update(Item item) {
        storage.updateAll(items);
    }
}
