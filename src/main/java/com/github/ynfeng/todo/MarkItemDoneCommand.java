package com.github.ynfeng.todo;

public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(String[] args, Console console, ItemRepository itemRepository) {
        int itemIdx = Integer.valueOf(args[1]);
        Item item = itemRepository.getByIndex(itemIdx - 1);
        item.done();
        itemRepository.update(item);
        console.println("Item %d done", itemIdx);
    }
}
