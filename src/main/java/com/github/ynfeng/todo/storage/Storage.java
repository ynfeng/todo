package com.github.ynfeng.todo.storage;

import java.util.List;

public interface Storage<T> {
    List<T> loadAll();

    void append(T item);

    void updateAll(List<T> items);

    void appendAll(List<T> list);
}
