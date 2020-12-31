package com.github.ynfeng.todo.command;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.persistence.ItemRepository;

public interface Command {
    void execute(Args a, String[] args, ItemRepository itemRepository);
}
