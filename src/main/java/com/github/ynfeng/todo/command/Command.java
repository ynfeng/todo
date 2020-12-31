package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.persistence.ItemRepository;

public interface Command {
    void execute(String[] args, ItemRepository itemRepository);
}
