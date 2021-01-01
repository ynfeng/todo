package com.github.ynfeng.todo.persistence;

import com.github.ynfeng.todo.Item;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    void add(Item item);

    Optional<Item> getByIndex(int index);

    List<Item> listUnFinishedItem();

    List<Item> listAll();

    void update(Item item);
}
