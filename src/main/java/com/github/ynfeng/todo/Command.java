package com.github.ynfeng.todo;

public interface Command {
    void execute(String[] args, ItemRepository itemRepository);
}
