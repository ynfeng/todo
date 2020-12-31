package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.persistence.ItemRepository;

@FunctionalInterface
public interface Command {
    void execute(Args args, ItemRepository itemRepository);
}
