package com.github.ynfeng.todo.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryBasedItemRepository implements ItemRepository {
    public final List<Item> items = new ArrayList<>();

    @Override
    public void add(Item item) {
        items.add(item);
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

    }

}
