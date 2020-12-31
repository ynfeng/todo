package com.github.ynfeng.todo;

import static com.github.ynfeng.todo.Console.println;

public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(String[] args, ItemRepository itemRepository) {
        int itemIdx = Integer.parseInt(args[1]);
        Item item = itemRepository.getByIndex(itemIdx - 1);
        item.done();
        itemRepository.update(item);
        println("Item %d done", itemIdx);
    }
}
