package com.github.ynfeng.todo.persistence;

import com.github.ynfeng.todo.Item;
import java.util.List;

public interface ItemRepository {
    void add(Item item);

    Item getByIndex(int index);

    List<Item> listUnFinishedItem();

    List<Item> listAll();

    void update(Item item);
}
